package com.example.unittestfirst.dagger2;

import com.example.unittestfirst.mockito.PasswordValidator;

import javax.inject.Inject;

/**
 * Created by xiaochuang on 4/29/16.
 */
public class LoginPresenter {
    private final UserManager mUserManager;//UserManager对象
    private final PasswordValidator mPasswordValidator;//密码检测

    //@Inject
    public LoginPresenter(UserManager userManager, PasswordValidator passwordValidator) {
        System.out.println("调用了LoginPresenter  构造函数");
        this.mUserManager = userManager;
        this.mPasswordValidator = passwordValidator;
    }

    public void login(String username, String password) {
        if (username == null || username.length() == 0) return;
        if (!mPasswordValidator.verifyPassword(password)) return;

        System.out.println("LoginPresenter：login  mUserManager："+mUserManager);

        mUserManager.performLogin(username, password);
    }

    //是否已登录
    public boolean isLoggedIn() {
        return false;
    }

}
