package com.example.unittestfirst.test.mockitoannotations;

import com.example.unittestfirst.dagger2.UserManager;
import com.example.unittestfirst.mockito.PasswordValidator;
import com.example.unittestfirst.mockitoannotations.LoginPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by xiaochuang on 5/15/16.
 *
 *三种injection的优先级顺序分别为：
 * Constructor Injection > Property Setter Injection > Field Injection。
 * 具体情况可以在这里看到。很多人其实都不推荐使用@InjectMocks，因为很难弄清楚到底会通过哪种方式inject，
 * 我个人对这点也感觉有点别扭，我宁愿通过new LoginPresenter(mockUserMananger,
 * mockValiator)这种方式来创建LoginPresenter对象，而不是使用@InjectMocks
 *
 */
public class LoginPresenterTest {

    //常规写法
//    @Test
//    public void testLogin() {
//        UserManager mockUserManager = mock(UserManager.class);
//        PasswordValidator mockValidator = mock(PasswordValidator.class);
//        Mockito.when(mockValidator.verifyPassword("xiaochuang is handsome")).thenReturn(true);
//
//        System.out.println("setup  mockUserManager："+mockUserManager+",  mockValidator: "+mockValidator);
//
//        LoginPresenter presenter = new LoginPresenter(mockUserManager, mockValidator);
//
//        presenter.login("xiaochuang", "xiaochuang is handsome");
//        verify(mockUserManager).performLogin("xiaochuang", "xiaochuang is handsome");
//    }

/********************************************/
    //初步简化
//    UserManager mockUserManager;
//    PasswordValidator mockValidator;
//    LoginPresenter loginPresenter;
//
//    @Before
//    public void setup() {
//        mockUserManager = mock(UserManager.class);
//        mockValidator = mock(PasswordValidator.class);
//        System.out.println("setup  mockUserManager："+mockUserManager+",  mockValidator: "+mockValidator);
//        loginPresenter = new LoginPresenter(mockUserManager, mockValidator);
//    }
//
//    @Test
//    public void testLogin() {
//        System.out.println("testLogin");
//
//        Mockito.when(mockValidator.verifyPassword("xiaochuang is handsome")).thenReturn(true);
//        loginPresenter.login("xiaochuang", "xiaochuang is handsome");
//
//        verify(mockUserManager).performLogin("xiaochuang", "xiaochuang is handsome");
//    }

    /********************************************/
    //再进一步简化
//    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule();
//
//    @Mock
//    UserManager mockUserManager;
//
//    @Mock
//    PasswordValidator mockValidator;
//
//    LoginPresenter loginPresenter;
//
//    @Before
//    public void setup() {
//        System.out.println("setup  mockUserManager："+(mockUserManager == null)+",  mockValidator: "+(mockValidator == null));
//        MockitoAnnotations.initMocks(this);//要么加@Rule，要么加这句话
//        System.out.println("setup---initMocks   mockUserManager："+(mockUserManager == null)+",  mockValidator: "+(mockValidator == null));
//
//        loginPresenter = new LoginPresenter(mockUserManager, mockValidator);
//    }
//
//    @Test
//    public void testLogin() {
//        System.out.println("testLogin()");
//
//        Mockito.when(mockValidator.verifyPassword("xiaochuang is handsome")).thenReturn(true);
//        loginPresenter.login("xiaochuang", "xiaochuang is handsome");
//        verify(mockUserManager).performLogin("xiaochuang", "xiaochuang is handsome");
//
//        System.out.println("testLogin  mockUserManager："+mockUserManager+",  mockValidator: "+mockValidator);
//    }


    /********************************************/
    //最终简化
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    UserManager mockUserManager;
    @Mock
    PasswordValidator mockValidator;

    @InjectMocks
    LoginPresenter loginPresenter;

    @Test
    public void testLogin() {
        Mockito.when(mockValidator.verifyPassword("xiaochuang is handsome")).thenReturn(true);
        loginPresenter.login("xiaochuang", "xiaochuang is handsome");

        verify(mockUserManager).performLogin("xiaochuang", "xiaochuang is handsome");
    }

}