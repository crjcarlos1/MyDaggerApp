package com.cralos.mydaggerapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.cralos.mydaggerapp.models.User;
import com.cralos.mydaggerapp.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Debe de encontrarse al nivel el appComponent, ya que se encuentra en cualquier parte de la aplicación
 */
@Singleton
public class SessionManager {
    private static final String TAG = "SessionManager";

    private MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    public void authenticateWithId(final LiveData<AuthResource<User>> source) {
        if (cachedUser != null) {
            cachedUser.setValue(AuthResource.loading((User) null));
            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);
                }
            });
        }
    }

    public void logOut() {
        Log.e(TAG, "logOut...");
        cachedUser.setValue(AuthResource.<User>logout(null));
    }

    public LiveData<AuthResource<User>> getAuthUser() {
        return cachedUser;
    }

}
