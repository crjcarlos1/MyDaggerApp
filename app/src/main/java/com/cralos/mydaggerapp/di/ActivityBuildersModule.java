package com.cralos.mydaggerapp.di;

import com.cralos.mydaggerapp.di.auth.AuthModule;
import com.cralos.mydaggerapp.di.auth.AuthViewModelsModule;
import com.cralos.mydaggerapp.di.main.MainFragmentBuildersModule;
import com.cralos.mydaggerapp.ui.auth.AuthActivity;
import com.cralos.mydaggerapp.ui.main.MainActivity;

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

    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class}    //le decimos que 'MainFragmentBuildersModule' es un subcomponente y solo vive en mainActivity
    )
    abstract MainActivity contributeMainActivity();

}
