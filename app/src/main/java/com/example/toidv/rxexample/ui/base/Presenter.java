package com.example.toidv.rxexample.ui.base;

/**
 * Created by TOIDV on 5/24/2016.
 */
public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
