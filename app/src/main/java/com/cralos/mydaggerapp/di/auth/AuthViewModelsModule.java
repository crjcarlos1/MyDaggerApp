package com.cralos.mydaggerapp.di.auth;

import androidx.lifecycle.ViewModel;

import com.cralos.mydaggerapp.di.ViewModelKey;
import com.cralos.mydaggerapp.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)  /** La anotación 'ViewModelKey' nosotros la creamos como un interface */
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);


}
