package com.example.unittestfirst.dagger2;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by xiaochuang on 5/10/16.
 */
public class UserManager {
    private final SharedPreferences mPref;//本地存储  用于存储一些用户的基本设置
    private final UserApiService mRestAdapter;//标签接口，里面没有方法

    @Inject
    public UserManager(SharedPreferences preferences, UserApiService userApiService) {
        this.mPref = preferences;
        this.mRestAdapter = userApiService;
    }

    public UserManager() {
        mPref = null;
        mRestAdapter = null;
    }

    //登录
    public void performLogin(String username, String password) {
    }

    //注册
    public void performRegister(String username, String password) {
    }
}
