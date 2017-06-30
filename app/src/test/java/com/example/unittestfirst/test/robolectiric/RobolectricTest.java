package com.example.unittestfirst.test.robolectiric;

import android.app.RobolectricActivityManager;
import android.content.Intent;

import com.example.unittestfirst.BuildConfig;
import com.example.unittestfirst.R;
import com.example.unittestfirst.robolectric.RobolectricActivity;
import com.example.unittestfirst.robolectric.SecondActivity;
import com.example.unittestfirst.what.MainActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

/**
 * Created by pipi on 2017/6/26.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RobolectricTest {
    @Test
    public void testRobolectric() {
        RobolectricActivity robolectricActivity = Robolectric.setupActivity(RobolectricActivity.class);
        robolectricActivity.findViewById(R.id.textview1).performClick();

        Intent expectedIntent = new Intent(robolectricActivity, SecondActivity.class);
        System.out.println("testRobolectric:expectedIntent----"+expectedIntent);
        ShadowActivity shadowActivity = Shadows.shadowOf(robolectricActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        Assert.assertEquals(expectedIntent, actualIntent);
    }
}
