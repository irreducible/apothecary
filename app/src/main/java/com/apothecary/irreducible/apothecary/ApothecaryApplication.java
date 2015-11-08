package com.apothecary.irreducible.apothecary;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by amore on 11/8/15.
 */
public class ApothecaryApplication extends Application {
    public static final String YOUR_APPLICATION_ID = "SQNbAwj2xD69BNlZCI0tkJj1OmLBNBAjNqNI49eJ";
    public static final String YOUR_CLIENT_KEY = "Rg7y2LgaId7t0ZmdMwwRTP7d8keuB7L7LwZnGlb0";
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
    }
}
