package com.example.unittestfirst.test.daggermock;

import com.example.unittestfirst.dagger2.AppComponent;
import com.example.unittestfirst.dagger2.AppModule;
import com.example.unittestfirst.dagger2.ComponentHolder;
import com.example.unittestfirst.dagger2.DaggerAppComponent;

import org.robolectric.RuntimeEnvironment;

import static org.mockito.Mockito.spy;

/**
 * Created by xiaochuang on 5/15/16.
 */
public class TestUtils {
    //创建一个mockAppModule,这里不能spy(AppModule.class),因为AppModule没有默认无参数的Constructor，也不能mock(AppModule.class)
    //原因是dagger2的约束，Provider方法不能返回null，除非用@Nullable修饰
    public static final AppModule appModule = spy(new AppModule(RuntimeEnvironment.application));//运行时应用

    public static void setupDagger() {
        AppComponent appComponent = DaggerAppComponent.builder().appModule(appModule).build();
        ComponentHolder.setAppComponent(appComponent);
    }

}
