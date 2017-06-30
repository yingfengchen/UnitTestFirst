package com.example.unittestfirst.test.dagger2;

import android.content.SharedPreferences;

import com.example.unittestfirst.BuildConfig;
import com.example.unittestfirst.dagger2.ComponentHolder;
import com.example.unittestfirst.dagger2.LoginPresenter;
import com.example.unittestfirst.dagger2.UserApiService;
import com.example.unittestfirst.dagger2.UserManager;
import com.example.unittestfirst.mockito.PasswordValidator;
import com.example.unittestfirst.test.groupshare.JSpec;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by xiaochuang on 5/15/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginPresenterTest {

    //稍微复杂的方法
//    @Test
//    public void testLogin_daggerVersion() throws Exception {
//        TestUtils.setupDagger();
//        UserManager mockUserManager = mock(UserManager.class);
//        Mockito.when(TestUtils.appModule.provideUserManager(any(SharedPreferences.class), any(UserApiService.class))).thenReturn(mockUserManager);
//
//        System.out.println("虚拟mockUserManager："+mockUserManager);
//
//        LoginPresenter presenter = ComponentHolder.getAppComponent().loginPresenter();
//        presenter.login("xiaochuang", "xiaochuang is handsome");
//
//        verify(mockUserManager).performLogin("xiaochuang", "xiaochuang is handsome");
//    }

    ////稍微简单的方法，不用dagger2
    @Test
    @JSpec(desc = "should fail for mock is not used")
    public void testLogin() {
        UserManager mockUserManager = mock(UserManager.class);
        LoginPresenter presenter = new LoginPresenter(mockUserManager, new PasswordValidator());//因为这里我们不verify PasswordValidator，所以不需要mock这个。

        presenter.login("xiaochuang", "xiaochuang is handsome");

        verify(mockUserManager).performLogin("xiaochuang", "xiaochuang is handsome");
    }
}


/***********************************************/
//单元测试的时候，哪些情况应该用dagger2，那些情况不用呢？答案是，能不用dagger2，就不用dagger2，
//不得已用dagger2，才用dagger2。当然，这是一句废话，前面我们已经明显感受到了，
//在单元测试里面用dagger2比不用dagger2要麻烦多了，能不用当然不用。那么问题就变成了，
//什么情况下必须用dagger2、而什么时候可以不用呢？答案是，如果被测类(比如说LoginActivity)的
//Dependency(LoginPresenter)是通过 field injection inject进去的，
//那么再测这个类(LoginActivity)的时候，就必须用dagger2，
//不然很难优雅的把mock传进去。相反，如果被测类有Constructor(比如说LoginPresenter)，
//Dependency是通过Constructor传进去的，那么就可以不使用dagger2，
//而是直接new对象出来测。这也是为什么我在前一篇文章里面强烈的推荐 Constructor Injection的原因。