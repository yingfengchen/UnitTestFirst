package com.example.unittestfirst;

import android.app.Application;

import com.example.unittestfirst.dagger2.AppComponent;
import com.example.unittestfirst.dagger2.AppModule;
import com.example.unittestfirst.dagger2.ComponentHolder;
import com.example.unittestfirst.dagger2.DaggerAppComponent;

/**
 * Created by xiaochuang on 5/14/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppModule appModule = new AppModule(this);
        AppComponent appComponent = DaggerAppComponent.builder().appModule(appModule).build();
        ComponentHolder.setAppComponent(appComponent);
    }
}
