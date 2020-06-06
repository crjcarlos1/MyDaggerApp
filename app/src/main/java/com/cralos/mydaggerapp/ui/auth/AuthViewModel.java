package com.cralos.mydaggerapp.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.cralos.mydaggerapp.network.auth.AuthApi;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    /**Constructor por default*/
    /**
     * Se inyecta AuthApi en el constructor
     */
    @Inject
    public AuthViewModel(AuthApi authApi) {
        Log.e(TAG, "AuthViewModel is working ...");
        this.authApi = authApi;
        if (this.authApi == null) Log.e(TAG, "AuthApi es NULL");
        else Log.e(TAG, "AuthApi NO es null");
    }
}
