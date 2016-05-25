package com.example.toidv.rxexample.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.example.toidv.rxexample.RxExampleApplication;
import com.example.toidv.rxexample.injection.component.ActivityComponent;
import com.example.toidv.rxexample.injection.component.DaggerActivityComponent;

/**
 * Created by TOIDV on 5/24/2016.
 */
public class BaseActivity extends AppCompatActivity {

    ActivityComponent mActivityComponent;

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(RxExampleApplication.get(this).getComponent())
                    .build();
        }

        return mActivityComponent;
    }


}
