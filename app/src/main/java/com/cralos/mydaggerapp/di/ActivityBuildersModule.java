package com.cralos.mydaggerapp.di;

import com.cralos.mydaggerapp.AuthActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * En esta clase le decimos a dagger que activiti es potencialmnente un cliente que se puede inyectar
 */

@Module
public abstract class ActivityBuildersModule {      //usamos abstract cada que utilicemos la notación @ContributesAndroidInjector

    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();

}
