package com.cralos.mydaggerapp.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.cralos.mydaggerapp.models.User;
import com.cralos.mydaggerapp.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

        authApi.getUser(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User value) {
                        Log.e(TAG, "user: "+value.toString() );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
