package me.teamwith.teamwithme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * A login screen that offers login via Facebook.
 */
public class LoginActivity extends FragmentActivity {

    public static final String TAG = "TWM";

    private Fragment loginFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "Reached Login Activity.");

        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            loginFragment = new LoginFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, loginFragment)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            loginFragment = (LoginFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }
    }

//        ParseQuery currentUserQuery = ParseUser.getQuery();
//        currentUserQuery.whereEqualTo("username", "cupcake");
//        currentUserQuery.findInBackground(new FindCallback<ParseUser>() {
//            @Override
//            public void done(List<ParseUser> list, ParseException e) {
//
//                if (e == null) {
//                    // No ParseException
//
//                    String currentUserId = list.get(0).getObjectId();
//
//                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
//                    mainIntent.putExtra("EXTRA_CURRENT_USER_ID", currentUserId);
//                    startActivity(mainIntent);
//                } else {
//                    // ParseException
//
//                    Log.d(TAG, "Could not find user.");
//                }
//            }
//        });
}
