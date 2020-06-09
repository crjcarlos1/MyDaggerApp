package com.cralos.mydaggerapp.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cralos.mydaggerapp.R;
import com.cralos.mydaggerapp.models.User;
import com.cralos.mydaggerapp.ui.auth.AuthResource;
import com.cralos.mydaggerapp.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";

    private TextView email,username,website;

    private ProfileViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "profileFragment was created");
        email=view.findViewById(R.id.email);
        username=view.findViewById(R.id.username);
        website=view.findViewById(R.id.website);
        viewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel.class);
        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.getAuthenticateUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticateUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case AUTHENTICATE: {
                            setUserDetails(userAuthResource);
                            break;
                        }
                        case ERROR: {
                            setErrosDetails(userAuthResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void setErrosDetails(String message) {
        email.setText(message);
        username.setText("error");
        website.setText("error");
    }

    private void setUserDetails(AuthResource<User> userAuthResource) {
        email.setText(userAuthResource.data.getEmail());
        username.setText(userAuthResource.data.getUserName());
        website.setText(userAuthResource.data.getWebSite());
    }

}
