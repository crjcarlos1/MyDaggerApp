package com.cralos.mydaggerapp.ui.auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.cralos.mydaggerapp.R;
import com.cralos.mydaggerapp.models.User;
import com.cralos.mydaggerapp.ui.main.MainActivity;
import com.cralos.mydaggerapp.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Como estamos utilizando @ContributesAndroidInjector en la clase 'ActivityBuildersModule'
 * entonces debemos heredar de DaggerAppCompatActivity y esa clase hereda de 'AppCompatActivity'
 */
public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AuthActivity";

    private EditText userId;
    private ProgressBar progressBar;

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
        userId = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);
        (findViewById(R.id.login_button)).setOnClickListener(this);
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);
        setLogo();
        subscribeObservers();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                attempLogin();
                break;
        }
    }

    private void attempLogin() {
        if (TextUtils.isEmpty(userId.getText().toString())) {
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
    }

    private void subscribeObservers() {
        viewModel.observerAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case ERROR: {
                            showProgressBar(false);
                            Toast.makeText(getApplicationContext(), userAuthResource.message + " \n did you enter a number between 1 and 10", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case LOADING: {
                            showProgressBar(true);
                            break;
                        }
                        case AUTHENTICATE: {
                            showProgressBar(false);
                            Log.e(TAG, "onChanged: " + userAuthResource.data.toString());
                            onLoginSuccess();
                            break;
                        }
                        case NOT_AUTHENTICATE: {
                            showProgressBar(false);

                            break;
                        }
                    }
                }
            }
        });
    }

    private void onLoginSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProgressBar(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setLogo() {
        requestManager.load(logo).into((ImageView) findViewById(R.id.login_logo));
    }
}
