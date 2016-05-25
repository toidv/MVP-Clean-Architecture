package com.example.toidv.rxexample.data;

import com.example.toidv.rxexample.data.local.PreferenceHelper;
import com.example.toidv.rxexample.data.pojo.Repo;
import com.example.toidv.rxexample.data.pojo.SearchResult;
import com.example.toidv.rxexample.data.remote.GithubServices;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by TOIDV on 5/24/2016.
 */

@Singleton
public class DataManager {
    private final GithubServices mGithubService;
    private final PreferenceHelper mPreferenceHelper;

    @Inject
    public DataManager(GithubServices githubServices, PreferenceHelper preferenceHelper) {
        this.mGithubService = githubServices;
        this.mPreferenceHelper = preferenceHelper;
    }


    public Observable<SearchResult> searchRepo(String name, String sort) {
        return mGithubService.searchRepo(name, sort);
    }

    public Observable<Repo[]> getRepos(String url) {
        return mGithubService.getRepos(url);
    }
}
