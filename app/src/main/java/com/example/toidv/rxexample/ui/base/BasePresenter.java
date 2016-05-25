package com.example.toidv.rxexample.ui.base;

/**
 * Created by TOIDV on 5/24/2016.
 */
public class BasePresenter<V extends MvpView> implements Presenter<V> {
    private V mMvpView;

    @Override
    public void attachView(V mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;

    }


    public V getMvpView() {
        return mMvpView;
    }
}
