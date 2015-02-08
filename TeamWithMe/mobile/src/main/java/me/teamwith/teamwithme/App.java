package me.teamwith.teamwithme;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    public void onCreate() {

        super.onCreate();

        // Start Parse
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "v2hAXH2M27jXXzWT5ag45c72KFT4EfLuRdwSq5Ha", "tFKPvEQ4Ia5RiuipCktadRgd6i7ngceMWM94scbr");
        // End Parse
    }
}