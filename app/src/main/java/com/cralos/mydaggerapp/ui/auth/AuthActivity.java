package com.cralos.mydaggerapp.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.cralos.mydaggerapp.R;
import com.cralos.mydaggerapp.models.User;
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
        (findViewById(R.id.login_button)).setOnClickListener(this);
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);
        setLogo();
        subscribeObservers();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_button:
                attempLogin();
                break;
        }
    }

    private void attempLogin() {
        if (TextUtils.isEmpty(userId.getText().toString())){
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
    }


    private void subscribeObservers() {
        viewModel.observerUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user!=null){
                    Log.e(TAG, "onChanged: "+user.toString());
                }
            }
        });
    }

    private void setLogo() {
        requestManager.load(logo).into((ImageView) findViewById(R.id.login_logo));
    }
}
