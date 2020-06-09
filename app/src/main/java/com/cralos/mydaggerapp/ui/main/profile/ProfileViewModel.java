package com.cralos.mydaggerapp.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cralos.mydaggerapp.SessionManager;
import com.cralos.mydaggerapp.models.User;
import com.cralos.mydaggerapp.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";

    private final SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        Log.e(TAG, "ProfileViewModel is working ...");
        this.sessionManager = sessionManager;
    }

    public LiveData<AuthResource<User>> getAuthenticateUser() {
        return sessionManager.getAuthUser();
    }


}
