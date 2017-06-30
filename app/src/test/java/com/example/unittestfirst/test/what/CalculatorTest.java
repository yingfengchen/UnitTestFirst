package com.example.unittestfirst.test.what;
import com.example.unittestfirst.junit.Calculator;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by xiaochuang on 4/12/16.
 *
 * 除了在AndroidStudio里面运行，你还可以在命令行通过gradle testDebugUnitTest，
 * 或者是gradle testReleaseUnitTest，分别运行debug和release版本的unit testing，
 * 这种方式可以一次性运行所有测试类的所有测试方法
 *
 * 每个test case的报告可以在project_root/app/build/reports/tests/debug/index.html
 * 这个xml里面看到
 *
 *
 * MVC   MVP模式
 * Robolectric
 *
 * Mock的概念：所谓的mock就是创建一个类的虚假的对象，在测试环境中，用来替换掉真实的对象，以达到两大目的
 * 1、验证这个对象的某个方法的调用情况，调用了多少次，参数是什么等等
 * 1.指定这个对象的某些方法的行为，返回特定的值，或者是执行特定的动作
 *
 *
 */
public class CalculatorTest {

    Calculator mCalculator;

    @Before
    public void setup() {
        mCalculator = new Calculator();
    }

    @Test
    public void testAdd() throws Exception {
        int sum = mCalculator.add(1, 2);
        assertEquals(3, sum);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivide() {
        mCalculator.divide(4, 0);
    }

    @Test
    public void testDivide2() {
        double div = mCalculator.divide(4, 2);
        assertEquals(1.0D, div);
    }

    @Test
    @Ignore("not implemented yet")
    public void testFactorial() {
    }

}