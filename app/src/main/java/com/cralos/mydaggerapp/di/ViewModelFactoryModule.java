package com.cralos.mydaggerapp.di;

import androidx.lifecycle.ViewModelProvider;

import com.cralos.mydaggerapp.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

/**
 * Esta clase es la responsable de generar la dependencia, realizando la inyección de dependencias para El viewmodelfactory class
 */

@Module
abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);

}
