package com.example.unittestfirst.test.daggermock;

import com.example.unittestfirst.dagger2.AppComponent;
import com.example.unittestfirst.dagger2.AppModule;
import com.example.unittestfirst.dagger2.ComponentHolder;

import org.robolectric.RuntimeEnvironment;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Created by xiaochuang on 7/24/16.
 *
 *
 * dagger2的工作原理是，在你的java代码编译成字节码的过程中，
 * dagger2会对所有的Component(就是用 @Component修饰过的interface)进行处理，
 * 自动生成一个实现了这个interface的类，生成的类名是Component的名字前面加上“Dagger”。
 * 比如我们定义的 AppComponent，对应的自动生成的类叫做DaggerAppComponent。
 */
public class DaggerRule extends DaggerMockRule<AppComponent> {
    public DaggerRule() {
        //告诉DaggerMock要build什么样的Compoent,使用哪个module
        super(AppComponent.class, new AppModule(RuntimeEnvironment.application));

        //告诉DaggerMock把build好的Component放到哪
        set(new ComponentSetter<AppComponent>() {
            @Override
            public void setComponent(AppComponent appComponent) {
                ComponentHolder.setAppComponent(appComponent);
            }
        });
    }
}
