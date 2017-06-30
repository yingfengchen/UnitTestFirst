package com.example.unittestfirst.what;

/**
 * Created by xiaochuang on 4/12/16.
 */
public class Calculator {
    public int add(int one, int another) {
        return one + another;
    }

    public double divide(double divident, double dividor) {
        if (dividor == 0) throw new IllegalArgumentException("Dividor cannot be 0");

        return divident / dividor;
    }
}
