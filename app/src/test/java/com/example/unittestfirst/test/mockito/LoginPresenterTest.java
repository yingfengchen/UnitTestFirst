package com.example.unittestfirst.test.mockito;

import com.example.unittestfirst.groupshare.NetworkCallback;
import com.example.unittestfirst.mockito.LoginPresenter;
import com.example.unittestfirst.mockito.PasswordValidator;
import com.example.unittestfirst.test.groupshare.JSpec;
import com.example.unittestfirst.what.UserManager;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

/**
 * Created by xiaochuang on 4/29/16.
 *
 * 依赖注入，简单解释就是把UserManager作为LoginPresenter的构造函数的参数，传进去
 *
 * Mockito.verify(mockUserManager, Mockito.times(1)).performLogin("xiaochuang", "xiaochuang password");
 * 的简写，或者说重载方法，注意其中的Mockito.times(1)。
 * 因此，如果你想验证一个对象的某个方法得到了多次调用，只需要将次数传给Mockito.times()就好了。
 * Mockito.verify(mockUserManager, Mockito.times(3)).performLogin(...); //验证mockUserManager的performLogin得到了三次调用。
 * 对于调用次数的验证，除了可以验证固定的多少次，还可以验证最多，最少从来没有等等，方法分别是：atMost(count), atLeast(count), never()等等，
 * 都是Mockito的静态方法，其实大部分时候我们会static import Mockito这个类的所有静态方法，这样就不用每次加上Mockito.前缀了
 *
 * Mockito提供了一系列的any方法，来表示任何的参数都行：
 * Mockito.verify(mockUserManager).performLogin(Mockito.anyString(), Mockito.anyString());
 * anyString()表示任何一个字符串都可以。null？也可以的！
 * 类似anyString，还有anyInt, anyLong, anyDouble等等。anyObject表示任何对象，any(clazz)表示任何属于clazz的对象。
 * 在写这篇文章的时候，我刚刚发现，还有非常有意思也非常人性化的anyCollection，anyCollectionOf(clazz),
 * anyList(Map, set), anyListOf(clazz)等等
 *
 * mock两大作用   1、验证方法调用；2、指定某个方法的返回值，或者是执行特定的工作
 *
 */
public class LoginPresenterTest {

//    @Test
//    public void testLogin() throws Exception {
//        //最开始
////        LoginPresenter loginPresenter = new LoginPresenter();
////        loginPresenter.login("xiaochuang", "xiaochuang password");
////
////        //验证LoginPresenter里面的mUserManager的performLogin()方法得到了调用，同时参数分别是“xiaochuang”、“xiaochuang password”
////        //...
//
//
//        //Mock后   错误例子，未设置setter
//        Mockito.mock(UserManager.class);
//        LoginPresenter loginPresenter = new LoginPresenter();
//        loginPresenter.login("xiaochuang", "xiaochuang password");
//
//        UserManager userManager = loginPresenter.getUserManager();
//        Mockito.verify(userManager).performLogin("xiaochuang", "xiaochuang password");  //<==
//    }


    @Test
    public void testLogin() {
        //最终结果
        UserManager mockUserManager = Mockito.mock(UserManager.class);
        LoginPresenter loginPresenter = new LoginPresenter();
        loginPresenter.setUserManager(mockUserManager);
        loginPresenter.login("xiaochuang", "xiaochuang password");
        Mockito.verify(mockUserManager).performLogin(anyString(), anyString());//验证的是，mockUserManager的performLogin()方法得到了一次调用

//        //第四课mock    错误示例
//        Mockito.mock(UserManager.class);
//        LoginPresenter loginPresenter = new LoginPresenter();
//        loginPresenter.login("xiaochuang", "xiaochuang password");
//
//        UserManager userManager = loginPresenter.getUserManager();
//        //验证userManager的performLogin()方法得到了调用，参数分别是“xiaochuang”、“xiaochuang password”
//        //验证一个对象的方法调用情况的姿势是：Mockito.verify(objectToVerify).methodToVerify(arguments);
//        //其中，objectToVerify和methodToVerify分别是你想要验证的对象和方法。对应上面的例子，那就是：
//        //Mockito.verify(userManager).performLogin("xiaochuang", "xiaochuang password");
//        Mockito.verify(userManager).performLogin("xiaochuang", "xiaochuang password");  //<==

//        //第四课mock    正确示例
//        UserManager mockUserManager = Mockito.mock(UserManager.class);
//        LoginPresenter loginPresenter = new LoginPresenter();
//        loginPresenter.setUserManager(mockUserManager);
//        loginPresenter.login("xiaochuang", "xiaochuang password");
//
//        Mockito.verify(mockUserManager).performLogin("xiaochuang", "xiaochuang password");  //<==
    }

    @Test
    @JSpec(desc = "should mock return given value")
    public void testRetrunValue() {
        PasswordValidator mockValidator = Mockito.mock(PasswordValidator.class);//密码检验器
        Mockito.when(mockValidator.verifyPassword("xiaochuangishandsome")).thenReturn(true);
        Assert.assertEquals(true, mockValidator.verifyPassword("xiaochuangishandsome"));

        Mockito.when(mockValidator.verifyPassword(anyString())).thenReturn(true);
        Assert.assertEquals(true, mockValidator.verifyPassword("xiaochuangisnothandsome11"));
    }

    @Test
    public void testMockAndSpy() {
        PasswordValidator pValidator = Mockito.mock(PasswordValidator.class);//不会调用到方法内部
//        Mockito.doCallRealMethod().when(pValidator).verifyPassword(anyString());//指定了行为才会进入到方法内部
//        PasswordValidator pValidator = Mockito.spy(PasswordValidator.class);//会调用到方法内部
        pValidator.verifyPassword("xiaochuangishandsome");
        pValidator.verifyPassword("xiaochuangishandsome11");
    }

    @Test
    @JSpec(desc = "should mock perform certain action")
    public void testMockAnswer() {
        UserManager mockUserManager = Mockito.mock(UserManager.class);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                //这里可以获得传给performLogin的参数
                Object[] arguments = invocation.getArguments();

                NetworkCallback callback = (NetworkCallback) arguments[2];
                callback.onFailure(500, "Server error");

                System.out.println("返回500");

                return 500; //对于如果mock的是非void方法来说，这个将作为目标方法的返回值
            }
        }).when(mockUserManager).performLogin(anyString(), anyString(), any(NetworkCallback.class));

        mockUserManager.performLogin("xiaochuang", "xiaochuang password", Mockito.mock(NetworkCallback.class));
    }

    @Test
    public void testMockAnswer2() {
        UserManager mockUserManager = Mockito.mock(UserManager.class);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                //这里可以获得传给performLogin的参数
                Object[] arguments = invocation.getArguments();

                NetworkCallback callback = (NetworkCallback) arguments[2];
                callback.onFailure(400, "Server error");

                System.out.println("返回400");

                return 400; //对于如果mock的是非void方法来说，这个将作为目标方法的返回值
            }
        }).when(mockUserManager).performLogin(anyString(), anyString(), any(NetworkCallback.class));

        LoginPresenter loginPresenter = new LoginPresenter();
        loginPresenter.setUserManager(mockUserManager);
        loginPresenter.login("xiaochuang", "xiaochuang is handsome");
    }

    @Test
    public void testSpy() {
        //跟创建mock类似，只不过调用的是spy方法，而不是mock方法。spy的用法
        PasswordValidator spyValidator = Mockito.spy(PasswordValidator.class);

        //在默认情况下，spy对象会调用这个类的real implementation，并返回相应的返回值
        boolean result = spyValidator.verifyPassword("xiaochuang_is_handsome");//true
        Assert.assertTrue(result);
        result = spyValidator.verifyPassword("xiaochuang_is_not_handsome"); //false
        Assert.assertFalse(result);

        //也可以指定spy对象的方法的行为
        Mockito.when(spyValidator.verifyPassword(anyString())).thenReturn(true);
        result = spyValidator.verifyPassword("xiaochuang_is_not_handsome");
        System.out.println("testSpy  result: "+result);
        Assert.assertTrue(result);
        Mockito.verify(spyValidator, Mockito.times(2)).verifyPassword("xiaochuang_is_not_handsome");
    }

    @Test
    public void testMock() {
        //跟创建mock类似，只不过调用的是spy方法，而不是mock方法。spy的用法
        PasswordValidator mockValidator = Mockito.mock(PasswordValidator.class);

        //在默认情况下，spy对象会调用这个类的real implementation，并返回相应的返回值
        boolean result = mockValidator.verifyPassword("xiaochuang_is_handsome");//true
        Assert.assertTrue(result);
        result = mockValidator.verifyPassword("xiaochuang_is_not_handsome"); //false
        Assert.assertFalse(result);

        //也可以指定spy对象的方法的行为
        Mockito.when(mockValidator.verifyPassword(anyString())).thenReturn(true);
        result = mockValidator.verifyPassword("xiaochuang_is_not_handsome");
        System.out.println("testSpy  result: "+result);
        Assert.assertTrue(result);
        Mockito.verify(mockValidator, Mockito.times(2)).verifyPassword("xiaochuang_is_not_handsome");
    }
}