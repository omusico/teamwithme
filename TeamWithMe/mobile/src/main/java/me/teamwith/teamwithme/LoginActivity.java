package me.teamwith.teamwithme;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
    }
}
