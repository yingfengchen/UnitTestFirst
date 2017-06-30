package com.example.unittestfirst.mockito;

import com.example.unittestfirst.groupshare.NetworkCallback;
import com.example.unittestfirst.what.UserManager;

/**
 * Created by xiaochuang on 4/29/16.
 *
 *
 *
 */
public class LoginPresenter {

    private UserManager mUserManager = new UserManager();
    private PasswordValidator mPasswordValidator = new PasswordValidator();

//    public void login(String username, String password) {
//        if (username == null || username.length() == 0) return;
//        if (password == null || password.length() < 6) return;
//
//        mUserManager.performLogin(username, password);
//    }

//    public void login(String username, String password) {
//        if (username == null || username.length() == 0) return;
//        //假设我们对密码强度有一定要求，使用一个专门的validator来验证密码的有效性
//        if (!mPasswordValidator.verifyPassword(password)) return;
//
//        mUserManager.performLogin(username, password);
//    }

    public void login(String username, String password) {
        if (username == null || username.length() == 0) return;
        //假设我们对密码强度有一定要求，使用一个专门的validator来验证密码的有效性
        if (!mPasswordValidator.verifyPassword(password)) {
            System.out.println("不满足条件");
            return;
        } else {
            System.out.println("满足条件，密码是xiaochuang_is_handsome");
        }

        mUserManager.performLogin(username, password, new NetworkCallback() {
            @Override
            public void onSuccess(Object data) {
                //update view with data
            }

            @Override
            public void onFailure(int code, String msg) {
                //show error msg
                System.out.println("onFailure  code："+code);
            }
        });
    }

    /**
     * setter injection    argument injection(参数传递)   Constructor injection(依赖关系明显，也说明了这个类所完成的功能)
     * @param userManager
     */
    public void setUserManager(UserManager userManager) {
        this.mUserManager = userManager;
    }

    public void setPasswordValidator(PasswordValidator passwordValidator) {
        this.mPasswordValidator = passwordValidator;
    }

    public UserManager getUserManager() {  //<==
        return mUserManager;
    }

}
