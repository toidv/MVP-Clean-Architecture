package com.example.toidv.rxexample.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.toidv.rxexample.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by TOIDV on 5/24/2016.
 */

@Singleton
public class UserPrefs {

    private final SharedPreferences mSharePreferences;
    private final SharedPreferences.Editor mEditor;


    @Inject
    public UserPrefs(@ApplicationContext Context context) {
        mSharePreferences = context.getSharedPreferences(UserPrefConst.PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mSharePreferences.edit();
    }

    public String getUserName() {
        return mSharePreferences.getString(UserPrefConst.USER_NAME, "");
    }

    public void setUserName(String userName) {
        mEditor.putString(UserPrefConst.USER_NAME, userName);
        mEditor.commit();
    }

    public String getUserId() {
        return mSharePreferences.getString(UserPrefConst.USER_ID, "");
    }

    public void setUserId(int userId) {
        mEditor.putInt(UserPrefConst.USER_ID, userId);
        mEditor.commit();
    }

    public String getAvatarUrl() {
        return mSharePreferences.getString(UserPrefConst.AVATAR_URL, "");

    }

    public void setAvatarUrl(String avatarUrl) {
        mEditor.putString(UserPrefConst.AVATAR_URL, avatarUrl);
        mEditor.commit();
    }


}
