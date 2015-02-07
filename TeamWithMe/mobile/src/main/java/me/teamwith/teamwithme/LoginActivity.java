package me.teamwith.teamwithme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    public static final String TAG = "TWM";

    // UI references.
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "Reached Login Activity.");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginFormView = findViewById(R.id.login_form);

        ParseQuery currentUserQuery = ParseUser.getQuery();
        currentUserQuery.whereEqualTo("username", "cupcake");
        currentUserQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {

                if (e == null) {
                    // No ParseException

                    String currentUserId = list.get(0).getObjectId();

                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    mainIntent.putExtra("EXTRA_CURRENT_USER_ID", currentUserId);
                    startActivity(mainIntent);
                } else {
                    // ParseException

                    Log.d(TAG, "Could not find user.");
                }
            }
        });
    }
}
