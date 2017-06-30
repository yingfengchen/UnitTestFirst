package com.example.unittestfirst.dagger2;

/**
 * Created by xiaochuang on 5/10/16.
 *
 * UserApiService又需要由Retrofit创建，而Retrofit又用到OkHttpClient(比如说你要自己控制timeout、cache等东西)。
 */
public interface UserApiService {
}
