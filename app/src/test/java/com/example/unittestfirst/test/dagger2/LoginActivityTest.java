package com.example.unittestfirst.test.dagger2;

import android.widget.EditText;

import com.example.unittestfirst.BuildConfig;
import com.example.unittestfirst.R;
import com.example.unittestfirst.dagger2.LoginActivity;
import com.example.unittestfirst.dagger2.LoginPresenter;
import com.example.unittestfirst.dagger2.UserManager;
import com.example.unittestfirst.mockito.PasswordValidator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by xiaochuang on 5/14/16.
 */
@RunWith(RobolectricGradleTestRunner.class)    //Robolectric相关
@Config(constants = BuildConfig.class, sdk = 21)//Robolectric相关
public class LoginActivityTest {

    @Test
    public void testActivityStart() {
        TestUtils.setupDagger();//传一个mock的appModule来构造一个AppComponent并将其设置到ComponentHolder中去
        LoginPresenter mockLoginPresenter = mock(LoginPresenter.class);//创建一个mockLoginPresenter
        //当mockAppModule的provideLoginPresenter()方法被调用时，让它返回mockLoginPresenter
        Mockito.when(TestUtils.appModule.provideLoginPresenter(any(UserManager.class), any(PasswordValidator.class))).thenReturn(mockLoginPresenter);
        System.out.println("虚拟mockLoginPresenter："+mockLoginPresenter);

        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        ((EditText) loginActivity.findViewById(R.id.username)).setText("xiaochuang");
        ((EditText) loginActivity.findViewById(R.id.password)).setText("xiaochuang is handsome");
        loginActivity.findViewById(R.id.login).performClick();

        verify(mockLoginPresenter).login("xiaochuang", "xiaochuang is handsome");//pass
    }
}