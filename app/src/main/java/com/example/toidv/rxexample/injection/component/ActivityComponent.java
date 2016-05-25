package com.example.toidv.rxexample.injection.component;

import com.example.toidv.rxexample.injection.PerActivity;
import com.example.toidv.rxexample.injection.module.ActivityModule;
import com.example.toidv.rxexample.ui.home.HomeActivity;
import com.example.toidv.rxexample.ui.repo.ReposActivity;

import dagger.Component;

/**
 * Created by TOIDV on 5/24/2016.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(HomeActivity homeActivity);

    void inject(ReposActivity reposActivity);
}
