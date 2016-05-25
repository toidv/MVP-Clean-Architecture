package com.example.toidv.rxexample.ui.repo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.example.toidv.rxexample.R;
import com.example.toidv.rxexample.consts.Consts;
import com.example.toidv.rxexample.data.pojo.Repo;
import com.example.toidv.rxexample.ui.adapter.RepoAdapter;
import com.example.toidv.rxexample.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReposActivity extends BaseActivity implements ReposMvpView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @Inject
    ReposPresenter mReposPresenter;
    @BindView(R.id.recycler_view_favourite)
    RecyclerView mRecyclerView;
    private RepoAdapter mAdapter;
    private String mRepoUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        setupComponent();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mRepoUrl = extras.getString(Consts.REPO_URL);
            String title = extras.getString(Consts.USER_NAME);
            if (!TextUtils.isEmpty(title)) {
                getSupportActionBar().setTitle(title);
            }
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RepoAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setupComponent() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                getActivityComponent().inject(ReposActivity.this);
                mReposPresenter.attachView(ReposActivity.this);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        getRepos(mRepoUrl);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {

                    }
                });


    }


    private void getRepos(String url) {
        mReposPresenter.getRepos(url);
    }


    @Override
    public void addRepo(Repo repo) {
        mAdapter.addRepo(repo);
    }

    @Override
    protected void onDestroy() {
        if (mReposPresenter != null) {
            mReposPresenter.detachView();
        }
        super.onDestroy();
    }
}
