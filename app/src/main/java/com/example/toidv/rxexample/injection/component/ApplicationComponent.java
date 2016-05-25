package com.example.toidv.rxexample.injection.component;

import android.app.Application;
import android.content.Context;

import com.example.toidv.rxexample.data.DataManager;
import com.example.toidv.rxexample.data.local.PreferenceHelper;
import com.example.toidv.rxexample.data.local.UserPrefs;
import com.example.toidv.rxexample.data.remote.GithubServices;
import com.example.toidv.rxexample.injection.ApplicationContext;
import com.example.toidv.rxexample.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by TOIDV on 5/24/2016.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    Retrofit retrofit();

    GithubServices githubServices();

    UserPrefs userPrefs();

    PreferenceHelper preferenceHelper();

    DataManager dataManager();



}
