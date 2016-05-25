package com.example.toidv.rxexample;

import android.app.Application;
import android.content.Context;

import com.example.toidv.rxexample.injection.component.ApplicationComponent;
import com.example.toidv.rxexample.injection.component.DaggerApplicationComponent;
import com.example.toidv.rxexample.injection.module.ApplicationModule;

/**
 * Created by TOIDV on 5/24/2016.
 */
public class RxExampleApplication extends Application {

    ApplicationComponent mApplicationComponent;

    public static RxExampleApplication get(Context context) {
        return (RxExampleApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }

        return mApplicationComponent;
    }
}
