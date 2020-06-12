package com.cralos.mydaggerapp.di;

import com.cralos.mydaggerapp.di.auth.AuthModule;
import com.cralos.mydaggerapp.di.auth.AuthScope;
import com.cralos.mydaggerapp.di.auth.AuthViewModelsModule;
import com.cralos.mydaggerapp.di.main.MainFragmentBuildersModule;
import com.cralos.mydaggerapp.di.main.MainModule;
import com.cralos.mydaggerapp.di.main.MainScope;
import com.cralos.mydaggerapp.di.main.MainViewModelsModule;
import com.cralos.mydaggerapp.ui.auth.AuthActivity;
import com.cralos.mydaggerapp.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * En esta clase le decimos a dagger que activiti es potencialmnente un cliente que se puede inyectar
 */

@Module
public abstract class ActivityBuildersModule {      //usamos abstract cada que utilicemos la notación @ContributesAndroidInjector

    @AuthScope
    @ContributesAndroidInjector(modules = {AuthViewModelsModule.class, AuthModule.class})
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {                                 //le decimos que 'MainFragmentBuildersModule' es un subcomponente y solo vive en mainActivity
                    MainFragmentBuildersModule.class, MainViewModelsModule.class, MainModule.class})
    abstract MainActivity contributeMainActivity();

}
