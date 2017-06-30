package com.example.unittestfirst.test.daggermock;

import android.widget.EditText;

import com.example.unittestfirst.BuildConfig;
import com.example.unittestfirst.R;
import com.example.unittestfirst.dagger2.AppComponent;
import com.example.unittestfirst.dagger2.AppModule;
import com.example.unittestfirst.dagger2.ComponentHolder;
import com.example.unittestfirst.dagger2.DaggerAppComponent;
import com.example.unittestfirst.dagger2.LoginPresenter;
import com.example.unittestfirst.dagger2.UserManager;
import com.example.unittestfirst.daggermock.LoginActivity;
import com.example.unittestfirst.mockito.PasswordValidator;
import com.example.unittestfirst.test.dagger2.TestUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by xiaochuang on 5/14/16.
 *
 *
 * JUnit Rule的工作原理
 *
 * 初始化一个测试类里面的所有用@Mock field为mock对象(loginPresenter)
 * mock AppModule，通过反射的方式得到AppModule的所有provider方法，如果有某个方法的返回值是一个LoginPresenter，
 * 那么就使用Mockito，让这个方法（provideLoginPresenter(...))被调用时，返回我们在测试类里面定义的mock loginPresenter。
 * 使用这个mock AppModule来构建一个Component，并且放到ComponentHolder里面去。
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginActivityTest {

//    @Test
//    public void testLogin_olderversion() {
//        AppModule mockAppModule = mock(AppModule.class);
//        LoginPresenter mockLoginPresenter = mock(LoginPresenter.class);
//        Mockito.when(mockAppModule.provideLoginPresenter(any(UserManager.class), any(PasswordValidator.class))).thenReturn(mockLoginPresenter);  //当mockAppModule的provideLoginPresenter()方法被调用时，让它返回mockLoginPresenter
//        AppComponent appComponent = DaggerAppComponent.builder().appModule(mockAppModule).build();  //用mockAppModule来创建DaggerAppComponent
//        ComponentHolder.setAppComponent(appComponent);  //假设你的Component是放在ComponentHolder里面的
//
//        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
//        ((EditText) loginActivity.findViewById(R.id.username)).setText("xiaochuang");
//        ((EditText) loginActivity.findViewById(R.id.password)).setText("xiaochuang is handsome");
//        loginActivity.findViewById(R.id.login).performClick();
//
//        verify(mockLoginPresenter).login("xiaochuang", "xiaochuang is handsome");
//    }

    //旧的方式
//    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule();
//
//    @Mock
//    LoginPresenter loginPresenter;
//
//    @Test
//    public void testLogin_old_way() {
//        System.out.println("testLogin_old_way----loginPresenter："+(loginPresenter != null));
//
//        Mockito.when(TestUtils.appModule.provideLoginPresenter(any(UserManager.class), any(PasswordValidator.class))).thenReturn(loginPresenter);  //当mockAppModule的provideLoginPresenter()方法被调用时，让它返回mockLoginPresenter
//        TestUtils.setupDagger();
//
//        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
//        ((EditText) loginActivity.findViewById(R.id.username)).setText("xiaochuang");
//        ((EditText) loginActivity.findViewById(R.id.password)).setText("xiaochuang is handsome");
//        loginActivity.findViewById(R.id.login).performClick();
//
//        verify(loginPresenter).login("xiaochuang", "xiaochuang is handsome");
//    }

    //简洁代码
    @Rule
    public DaggerRule daggerRule = new DaggerRule();

    @Mock
    LoginPresenter loginPresenter;

    @Test
    public void testLogin_shinny_way() {//shinny  简易
        System.out.println("testLogin_shinny_way----loginPresenter："+(loginPresenter != null));

        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        ((EditText) loginActivity.findViewById(R.id.username)).setText("xiaochuang");
        ((EditText) loginActivity.findViewById(R.id.password)).setText("xiaochuang is handsome");
        loginActivity.findViewById(R.id.login).performClick();

        verify(loginPresenter).login("xiaochuang", "xiaochuang is handsome");
    }

}