package com.cralos.mydaggerapp.di;

import android.app.Application;

import com.cralos.mydaggerapp.BaseApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 *  Los componentes son los encargados de realizar los inject
 */

@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class    //aquí están todos los módulos que va a necesitar 'AppComponent' para hacer lo que quiero que haga
        })
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
