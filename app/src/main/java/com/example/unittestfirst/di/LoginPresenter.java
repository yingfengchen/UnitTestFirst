package com.example.unittestfirst.di;

import com.example.unittestfirst.what.UserManager;

/**
 * Created by xiaochuang on 4/29/16.
 *
 * 代码里面的类，一般分为两种，一种是Data类，比如说UserInfo, OrderInfo等等；另外一种是Service类，比如
 * UserManagr,AudioPlayer等等
 * 如果Constructor里面传入的很多是基本类型的数据或数据类，那么或许你要做的，是创建一个(或者另一个)数据类把这些数据封转一下
 * 如果传入的很多是Service类，那么说明这个类做的事情太多了，不符合单一职责的原则
 */
public class LoginPresenter {

//    private UserManager mUserManager = new UserManager();
    private UserManager mUserManager;

    public LoginPresenter(UserManager userManager) {
        this.mUserManager = userManager;
    }

    public void login(String username, String password) {
        if (username == null || username.length() == 0) return;
        if (password == null || password.length() < 6) return;

        mUserManager.performLogin(username, password);
    }


    /**
     * 通过方法参数来做DI的例子（Argument Injection）
     */
    public void login(UserManager userManager, String username, String password) {
        if (username == null || username.length() == 0) return;
        if (password == null || password.length() < 6) return;

        userManager.performLogin(username, password);
    }

}
