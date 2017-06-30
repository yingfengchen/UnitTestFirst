package com.example.unittestfirst.dagger2;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.example.unittestfirst.R;
import com.example.unittestfirst.mockito.PasswordValidator;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

//    LoginPresenter mLoginPresenter;

    @Inject   //Field injection
    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //非注入实现
//        AppComponent appCompent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
//        mLoginPresenter = appCompent.loginPresenter();

        //Field injection
        //DaggerAppComponent实现这个方法的方式是，去LoginActivity里面所有被 @Inject修饰的field，
        // 然后调用 AppModule相应的Provider方法，赋值给这个field。这里需要注意的是，@Inject field不能是private，
        // 不然dagger2找不到这个field,这种方式不支持继承，比如说LoginActivity继承自一个 BaseActivity，而@Inject
        //StatManager mStatManager;是放在BaseActivity里面的，那么在LoginActivity里面调用 appComponent.inject(this);
        // 并不会让BaseActivity里面的 mStatManager得到实例化，你必须在 BaseActivity里面也调用一次appComponent.inject(this);。
//        AppComponent appCompent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
//        appCompent.inject(this);//<==

        System.out.println("inject前："+mLoginPresenter);
        ComponentHolder.getAppComponent().inject(this);
        System.out.println("inject后："+mLoginPresenter);

        findViewById(R.id.login).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.username)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();

                System.out.println("点击mLoginPresenter："+mLoginPresenter);
                mLoginPresenter.login(username, password);
            }
        });
    }
}


//正常的复杂代码
//public class LoginActivity extends AppCompatActivity {
//    private LoginPresenter mLoginPresenter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        OkHttpClient okhttpClient = new OkHttpClient.Builder()
//                .connectTimeout(30, TimeUnit.SECONDS)
//                .build();
//        Retrofit retrofit = new Retrofit.Builder()//Retrofit又用到OkHttpClient(比如说要控制timeout,cache等东西)
//                .client(okhttpClient)
//                .baseUrl("https://api.github.com")
//                .build();
//        UserApiService userApiService = retrofit.create(UserApiService.class);//UserApiService又需要由Retrofit创建
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        UserManager userManager = new UserManager(preferences, userApiService);
//
//        PasswordValidator passwordValidator = new PasswordValidator();
//        mLoginPresenter = new LoginPresenter(userManager, passwordValidator);
//    }
//}

//dagger2
//在dagger2里面负责生产这些dependency的统一工厂叫做module,所有的client最终是要从module里面获取dependeccy,
//然而他们不是直接要module要的，而是有一个的"工厂管理员"，负责接收client的要求，然后到Module里面去找到相应的
// dependency提供给client们，这个”工厂管理员“叫做component.基本上这是dagger2里面最重要的两个概念

//生产Dependency的工厂：Module
//dagger2里面的一个annotation @Module来标注一下，来表示这是一个Module，而不是一个普通的类
//我们说module是生产dependency的地方，对应到代码里面就是module里面有很多方法，这些方法做的事情就是创建Denpendency




















