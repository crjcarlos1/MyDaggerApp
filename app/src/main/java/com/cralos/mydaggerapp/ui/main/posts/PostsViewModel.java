package com.cralos.mydaggerapp.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.cralos.mydaggerapp.SessionManager;
import com.cralos.mydaggerapp.network.main.MainApi;

import javax.inject.Inject;

public class PostsViewModel extends ViewModel {
    private static final String TAG = "PostsViewModel";

    private final SessionManager sessionManager;
    private final MainApi mainApi;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi) {
        Log.e(TAG, "PostsViewModel is working ...");
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
    }
}
