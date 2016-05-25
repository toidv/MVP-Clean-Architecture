package com.example.toidv.rxexample.data.local;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by TOIDV on 5/24/2016.
 */

@Singleton
public class PreferenceHelper {
    private final UserPrefs mUserPrefs;


    @Inject
    public PreferenceHelper(UserPrefs userPrefs) {
        this.mUserPrefs = userPrefs;
    }


}
