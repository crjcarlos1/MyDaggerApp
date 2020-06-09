package com.cralos.mydaggerapp.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.cralos.mydaggerapp.SessionManager;
import com.cralos.mydaggerapp.models.User;
import com.cralos.mydaggerapp.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;
    private SessionManager sessionManager;

    /**
     * Constructor por default
     * Se inyecta AuthApi en el constructor
     */
    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
        Log.e(TAG, "AuthViewModel is working ...");
        this.sessionManager = sessionManager;
        this.authApi = authApi;
    }

    public LiveData<AuthResource<User>> observerAuthState() {
        return sessionManager.getAuthUser();
    }

    public void authenticateWithId(int id) {
        Log.e(TAG, "attemting to login");
        sessionManager.authenticateWithId(queryUserId(id));
    }

    private LiveData<AuthResource<User>> queryUserId(int id){
        return LiveDataReactiveStreams.fromPublisher(authApi
                .getUser(id)
                .onErrorReturn(new Function<Throwable, User>() {
                    @Override
                    public User apply(Throwable throwable) throws Exception {
                        User errorUser = new User();
                        errorUser.setId(-1);
                        return errorUser;
                    }
                })
                .map(new Function<User, AuthResource<User>>() {
                    @Override
                    public AuthResource<User> apply(User user) throws Exception {
                        if (user.getId() == -1) {
                            return AuthResource.error("could not authenticate", null);
                        }
                        return AuthResource.authenticated(user);
                    }
                })
                .subscribeOn(Schedulers.io())
        );
    }

}
