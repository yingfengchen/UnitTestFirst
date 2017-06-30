package com.example.unittestfirst.robolectric;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.unittestfirst.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        TextView textView = (TextView)findViewById(R.id.textview2);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, SecondActivity.class));
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




















