package com.cralos.mydaggerapp.di.main;

import com.cralos.mydaggerapp.network.main.MainApi;
import com.cralos.mydaggerapp.ui.main.posts.PostsRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    public static PostsRecyclerAdapter providesAdapter() {
        return new PostsRecyclerAdapter();
    }

    @MainScope
    @Provides
    public static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

}
