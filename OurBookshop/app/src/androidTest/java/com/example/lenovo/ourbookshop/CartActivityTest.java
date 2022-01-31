package com.example.lenovo.ourbookshop;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class CartActivityTest {
    @Rule
    public ActivityTestRule<CartActivity> mActivityTestRule =new ActivityTestRule<CartActivity>(CartActivity.class);
    private CartActivity mActivity=null;
    Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(ConfirmOrderActivity.class.getName(),null,false);
    @Before
    public void setUp() throws Exception {
        mActivity= mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchofConfirmOrderActivityOnButtonClick(){
        assertNotNull(mActivity.findViewById(R.id.proceed_btn));
        onView(withId(R.id.proceed_btn)).perform(click());
        Activity confirmOrderActivity=getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(confirmOrderActivity);
        confirmOrderActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }
}