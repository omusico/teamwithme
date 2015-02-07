package me.teamwith.teamwithme;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

//import com.firebase.client.AuthData;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;

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

//        Firebase ref = new Firebase("https://teamwithme.firebaseio.com");
//        ref.authWithOAuthToken("github", "", new Firebase.AuthResultHandler() {
//            @Override
//            public void onAuthenticated(AuthData authData) {
//                // the GitHub user is now authenticated with Firebase
//            }
//
//            @Override
//            public void onAuthenticationError(FirebaseError firebaseError) {
//                // there was an error
//            }
//        });
    }

 }