package com.example.toidv.rxexample.injection.module;

import android.app.Activity;
import android.content.Context;

import com.example.toidv.rxexample.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by TOIDV on 5/24/2016.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }


    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }
}
