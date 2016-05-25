package com.example.toidv.rxexample.injection.module;

import android.app.Application;
import android.content.Context;

import com.example.toidv.rxexample.data.remote.GithubServices;
import com.example.toidv.rxexample.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by TOIDV on 5/24/2016.
 */

@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;

    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofitInstance() {
        return GithubServices.Creator.newRetrofitInstance();
    }


    @Provides
    @Singleton
    GithubServices provideGithubService(Retrofit retrofit) {
        return retrofit.create(GithubServices.class);
    }

}
