package com.example.toidv.rxexample.ui.home;

import com.example.toidv.rxexample.data.DataManager;
import com.example.toidv.rxexample.data.pojo.Item;
import com.example.toidv.rxexample.ui.base.BasePresenter;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by TOIDV on 5/24/2016.
 */
public class HomePresenter extends BasePresenter<HomeMvpView> {
    private final DataManager mDataManager;
    private final Retrofit mRetrofit;
    private Subscription mSbuscription;


    @Inject
    public HomePresenter(DataManager dataManager, Retrofit retrofit) {
        this.mDataManager = dataManager;
        this.mRetrofit = retrofit;
    }

    public void searchRepo(String search, String sort) {
        if (mSbuscription != null) {
            mSbuscription.unsubscribe();
        }
        mSbuscription = mDataManager.searchRepo(search, sort)
                .filter(searchResult -> searchResult != null)
                .concatMap(searchResult -> Observable.from(searchResult.getItems()))
                .filter(item -> item != null)
                .take(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> Observable.empty())
                .subscribe(new Subscriber<Item>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(Item item) {
                        getMvpView().addUser(item);

                    }
                });
    }

    @Override
    public void detachView() {
        if (mSbuscription != null) {
            mSbuscription.unsubscribe();
        }
        super.detachView();
    }
}
