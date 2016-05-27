package com.example.toidv.rxexample.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.toidv.rxexample.R;
import com.example.toidv.rxexample.consts.Consts;
import com.example.toidv.rxexample.data.pojo.Item;
import com.example.toidv.rxexample.ui.adapter.SearchAdapter;
import com.example.toidv.rxexample.ui.base.BaseActivity;
import com.example.toidv.rxexample.utils.RxExampleUtils;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeActivity extends BaseActivity implements HomeMvpView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_view_favourite)
    RecyclerView mRecyclerView;

    @BindView(R.id.progress_wheel)
    ProgressWheel mProgressWheel;

    @Inject
    HomePresenter mHomePresenter;


    private String mQuery;
    private SearchAdapter adapter;
    private SearchView mSearchView;

    private MaterialDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        createAlertDialog();
        setupComponent();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchAdapter();
        mRecyclerView.setAdapter(adapter);
    }

    private void setupComponent() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                getActivityComponent().inject(HomeActivity.this);
                mHomePresenter.attachView(HomeActivity.this);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        getDefaultUser();
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

    private void getDefaultUser() {
        mHomePresenter.searchRepo(Consts.DEFAULT_SEARCH, Consts.SORT_BY_FLOWER);
    }

    private void registerSearchListener() {
        RxSearchView.queryTextChanges(mSearchView)
                .filter(charSequence -> !TextUtils.isEmpty(charSequence))
                .throttleLast(100, TimeUnit.MILLISECONDS)
                .debounce(200, TimeUnit.MILLISECONDS)
                .onBackpressureLatest()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CharSequence>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();


                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        adapter.reset();
                        mHomePresenter.searchRepo(charSequence.toString(), Consts.SORT_BY_FLOWER);
                    }
                });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            mQuery = intent.getStringExtra(SearchManager.QUERY);
            getSupportActionBar().setTitle(mQuery);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        registerSearchListener();
        return true;


    }

    @Override
    protected void onDestroy() {
        if (mHomePresenter != null) {
            mHomePresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void addUser(Item item) {
        adapter.addItem(item);
    }

    @Override
    public void showProgress(boolean value) {
        if (value) {
            mProgressWheel.setVisibility(View.VISIBLE);
        } else {
            mProgressWheel.setVisibility(View.GONE);
        }
    }

    @Override
    public void createAlertDialog() {
        mAlertDialog = RxExampleUtils.createAlertDialog(this, getString(R.string.title_alert));
    }

    @Override
    public void showAlertDialog(String message) {
        mAlertDialog.setContent(message);
        mAlertDialog.show();
    }

}
