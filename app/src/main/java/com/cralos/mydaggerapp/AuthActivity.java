package com.cralos.mydaggerapp;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Como estamos utilizando @ContributesAndroidInjector en la clase 'ActivityBuildersModule'
 * entonces debemos heredar de DaggerAppCompatActivity y esa clase hereda de 'AppCompatActivity'
 */
public class AuthActivity extends DaggerAppCompatActivity {

    private static final String TAG = "AuthActivity";

    @Inject
    String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Log.e(TAG, "myString: " + string);
    }

}
