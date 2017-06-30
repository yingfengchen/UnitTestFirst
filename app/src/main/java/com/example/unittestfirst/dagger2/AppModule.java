package com.example.unittestfirst.dagger2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.unittestfirst.mockito.PasswordValidator;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by xiaochuang on 5/11/16.
 *
 * 一个Module就是一个类，这个类有一些生产Dependency的方法，但它也可以有一些正常的，不是用来生产Dependency
 * 的方法。dagger2规定，所有生产Dependency的方法必须用@Provides这个annotation标注一下
 */
@Module
public class AppModule {

    private final Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides     //这种用来生产Dependency的，用provides修饰过的方法叫Provider方法
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        return okhttpClient;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okhttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okhttpClient)
                .baseUrl("https://api.github.com")
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public UserApiService provideUserApiService(Retrofit retrofit) {
        System.out.println("调用了一次provideUserApiService");
        return retrofit.create(UserApiService.class);
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context) {
        System.out.println("调用了一次provideSharedPreferences");
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    public UserManager provideUserManager(SharedPreferences preferences, UserApiService service) {
        System.out.println("调用了一次provideUserManager");
        return new UserManager(preferences, service);
    }

    @Provides
    public PasswordValidator providePasswordValidator() {
        System.out.println("调用了一次providePasswordValidator");
        return new PasswordValidator();
    }

    @Provides
    public LoginPresenter provideLoginPresenter(UserManager userManager, PasswordValidator validator) {
        System.out.println("调用了一次provideLoginPresenter");
        return new LoginPresenter(userManager, validator);
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }


}
