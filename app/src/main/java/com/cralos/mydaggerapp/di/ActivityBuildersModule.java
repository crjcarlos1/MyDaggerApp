package com.cralos.mydaggerapp.di;

import com.cralos.mydaggerapp.di.auth.AuthModule;
import com.cralos.mydaggerapp.di.auth.AuthViewModelsModule;
import com.cralos.mydaggerapp.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * En esta clase le decimos a dagger que activiti es potencialmnente un cliente que se puede inyectar
 */

@Module
public abstract class ActivityBuildersModule {      //usamos abstract cada que utilicemos la notación @ContributesAndroidInjector

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

}
