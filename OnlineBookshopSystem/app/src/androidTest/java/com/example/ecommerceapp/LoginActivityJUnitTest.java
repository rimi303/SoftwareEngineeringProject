package com.example.ecommerceapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerceapp.Model.Users;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


//import android.widget.Button;
//import android.widget.EditText;
//@RunWith(MockitoJUnitRunner.class)

//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;


/**
 * A simple {@link LoginActivityJUnitTest} subclass for unit testing.
 * Activities that contain this LoginActivityJUnitTest must implement the
 * to handle interaction events.
 * Use the {@link Intent#Intent} factory method to testLogin()
 * create no instance of this LoginActivityJUnitTest.
 */

public class LoginActivityJUnitTest {

        @Rule
        public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

        public LoginActivity loginActivity;

        @Before
        public void setUp() throws Exception {
            loginActivity = mActivityTestRule.getActivity();
        }

    /**
     * Use this factory method to test a new activity of LoginActivity
     * this method using the provided  no parameter.
     *
     * @return nothing of method testLogin.
     */
        @Test
        public void testLogin(){
            getInstrumentation().runOnMainSync(new Runnable() {
                @Override
                public void run() {
                    EditText Phone = loginActivity.findViewById(R.id.login_phone_input);
                    EditText Password = loginActivity.findViewById(R.id.login_password_input);
                    Phone.setText("Phone");
                    Password.setText("Password");
                    Button LoginButton = loginActivity.findViewById(R.id.login_btn);
                    LoginButton.performClick();
                    assertTrue(loginActivity.IsCurrentUserLoggedIn());
                }
            });
        }

        @After
        public void tearDown() throws Exception {
            loginActivity = null;
        }
    }


