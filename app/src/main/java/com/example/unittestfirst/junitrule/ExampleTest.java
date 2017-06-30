package com.example.unittestfirst.junitrule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.concurrent.TimeUnit;

/**
 * Created by pipi on 2017/6/26.
 */

public class ExampleTest {
    @Rule
    public Timeout timeout = new Timeout(1000,TimeUnit.MILLISECONDS);//使用Timeout这个Rule

    @Test
    public void testMethod1() throws Exception {
//        Thread.sleep(2000);
    }

    @Test
    public void testMethod2() throws Exception {

    }

}
