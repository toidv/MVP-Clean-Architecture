package com.example.toidv.rxexample.ui.repo;

import com.example.toidv.rxexample.data.DataManager;
import com.example.toidv.rxexample.data.pojo.Repo;
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
public class ReposPresenter extends BasePresenter<ReposMvpView> {
    private final DataManager mDataManager;
    private final Retrofit mRetrofit;
    private Subscription mSbuscription;


    @Inject
    public ReposPresenter(DataManager dataManager, Retrofit retrofit) {
        this.mDataManager = dataManager;
        this.mRetrofit = retrofit;
    }


    @Override
    public void detachView() {
        if (mSbuscription != null) {
            mSbuscription.unsubscribe();
        }
        super.detachView();
    }

    public void getRepos(String url) {
        mSbuscription = mDataManager.getRepos(url)
                .filter(repos -> repos != null)
                .concatMap(repos -> Observable.from(repos))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Repo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Repo repo) {
                        getMvpView().addRepo(repo);
                    }
                });


    }
}
