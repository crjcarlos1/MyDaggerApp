package com.cralos.mydaggerapp.di.main;

import com.cralos.mydaggerapp.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Aquí es donde vamos a poner todos los inyectores para los fragments
 */
@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();
}
