package com.example.unittestfirst.test.junitrule;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Rule
    public MethodNameExample methodNameExample = new MethodNameExample();

    @Test
    public void addition_isCorrect() throws Exception {//加法是否正确
        assertEquals(4, 2 + 2);
    }

    @Test
    public void mulitiplication_isCorrect() throws Exception {//乘法是否正确
        assertEquals(4, 2 * 2);
    }
}