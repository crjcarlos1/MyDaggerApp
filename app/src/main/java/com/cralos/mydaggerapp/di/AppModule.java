package com.cralos.mydaggerapp.di;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Aqui van todas las dependencias que se encuentran en todas las pantallas de nuestra app,
 * por ejemplo, instancia de retrofit, glide ect.
 */

@Module
public class AppModule {

    @Provides
    static String provideString() {
        return "Hola mundo de Dagger";
    }

    @Provides
    static boolean getApp(Application application) {
        return application == null;
    }

}
