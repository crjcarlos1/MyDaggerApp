package com.cralos.mydaggerapp.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.cralos.mydaggerapp.R;
import com.cralos.mydaggerapp.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Como estamos utilizando @ContributesAndroidInjector en la clase 'ActivityBuildersModule'
 * entonces debemos heredar de DaggerAppCompatActivity y esa clase hereda de 'AppCompatActivity'
 */
public class AuthActivity extends DaggerAppCompatActivity {
    private static final String TAG = "AuthActivity";

    @Inject
    ViewModelProviderFactory providerFactory;

    private AuthViewModel viewModel;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        viewModel= ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);
        setLogo();
    }

    private void setLogo() {
        requestManager.load(logo).into((ImageView) findViewById(R.id.login_logo));
    }

}