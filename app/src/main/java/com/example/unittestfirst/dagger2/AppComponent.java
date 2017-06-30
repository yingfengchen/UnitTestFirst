package com.example.unittestfirst.dagger2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by xiaochuang on 5/14/16.
 *
 * 一个dagger2的Component需要用@Compeonent修饰一下
 *
 * 在实际情况下，可能有多个Module，也可能有多个Componnent
 *
 * dagger2的工作原理是，在你的java代码编译成字节码的过程中，dagger2会对所有的Component
 * (就是用 @Component修饰过的interface)进行处理，自动生成一个实现了这个interface的类，
 * 生成的类名是Component的名字前面加上“Dagger”。比如我们定义的 AppComponent，对应的自动生成的
 * 类叫做DaggerAppComponent。我们知道，实现一个interface需要实现里面的所有方法，
 * 因此，DaggerAppComponent是实现了 loginPresenter();这个方法的。
 * 实现的方式大致就是从 AppComponent管理的 AppModule里面去找LoginPresenter的Provider方法，
 * 然后调用这个方法，返回一个LoginPresenter。
 *
 *
 *
 * Declarative programming  说明性编程
 * Imperative Programming   命令式编程
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    LoginPresenter loginPresenter();//可注释，第一种方法

    void inject(LoginActivity mainActivity);
    void inject(com.example.unittestfirst.daggermock.LoginActivity loginActivity);
}
