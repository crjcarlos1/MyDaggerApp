package com.cralos.mydaggerapp.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    /**Constructor por default*/
    @Inject
    public AuthViewModel() {
        Log.e(TAG, "AuthViewModel is working ...");
    }
}
