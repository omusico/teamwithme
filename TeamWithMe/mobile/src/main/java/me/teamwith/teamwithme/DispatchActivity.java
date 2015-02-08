package me.teamwith.teamwithme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.parse.ParseUser;

public class DispatchActivity extends Activity {

    public static final String TAG = "TWM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "Reached Dispatch Activity.");

        // Start Parse Push Notifications
//        ParsePush.subscribeInBackground("", new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//
//                if (e == null) {
//                    Log.d("com.parse.push", "Successfully subscribed to Push.");
//                } else {
//                    Log.e("com.parse.push", "Failed to subscribe to Push.", e);
//                }
//            }
//        });
        // End Parse Push Notifications

        super.onCreate(savedInstanceState);

        // Check if there is current user info
        if (ParseUser.getCurrentUser() != null) {
            // Start an intent for the logged in activity
            startActivity(new Intent(this, MainActivity.class));
        } else {
            // Start and intent for the logged out activity
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
