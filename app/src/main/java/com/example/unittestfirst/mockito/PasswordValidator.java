package com.example.unittestfirst.mockito;

import javax.inject.Inject;

/**
 * Created by xiaochuang on 4/30/16.
 */
public class PasswordValidator {
    @Inject
    public PasswordValidator() {
    }

//    //验证密码    spy专用
//    public boolean verifyPassword(String password) {
//        //假设这个方法需要联网
//        boolean bPass = "xiaochuang_is_handsome".equals(password);
//        System.out.println("调用了PasswordValidator：verifyPassword  通过："+bPass);
//        return bPass;
//    }

    //验证密码
    public boolean verifyPassword(String password) {
        //假设这个方法需要联网
        boolean bPass = "xiaochuang is handsome".equals(password);
        System.out.println("调用了PasswordValidator：verifyPassword  通过："+bPass);
        return bPass;
    }
}
