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

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    /**
     * Constructor por default
     * Se inyecta AuthApi en el constructor
     */
    @Inject
    public AuthViewModel(AuthApi authApi) {
        Log.e(TAG, "AuthViewModel is working ...");
        this.authApi = authApi;
    }

    public LiveData<AuthResource<User>> observerUser() {
        return authUser;
    }

    public void authenticateWithId(int id) {
        /**LOADING*/
        authUser.setValue(AuthResource.loading((User)null));


        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(authApi
                .getUser(id)
                .onErrorReturn(new Function<Throwable, User>() {
                    @Override
                    public User apply(Throwable throwable) throws Exception {
                        User errorUser=new User();
                        errorUser.setId(-1);
                        return errorUser;
                    }
                })
                .map(new Function<User, AuthResource<User>>() {
                    @Override
                    public AuthResource<User> apply(User user) throws Exception {
                        if (user.getId() == -1){
                            return AuthResource.error("could not authenticate",null);
                        }
                        return AuthResource.authenticated(user);
                    }
                })
                .subscribeOn(Schedulers.io())
        );

        authUser.addSource(source, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });

    }

}
