package com.cralos.mydaggerapp.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.cralos.mydaggerapp.models.User;
import com.cralos.mydaggerapp.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    private MediatorLiveData<User> authUser = new MediatorLiveData<>();

    /**
     * Constructor por default
     * Se inyecta AuthApi en el constructor
     */
    @Inject
    public AuthViewModel(AuthApi authApi) {
        Log.e(TAG, "AuthViewModel is working ...");
        this.authApi = authApi;
    }

    public LiveData<User> observerUser() {
        return authUser;
    }

    public void authenticateWithId(int id) {
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(authApi.getUser(id).subscribeOn(Schedulers.io())
        );

        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });

    }

}
