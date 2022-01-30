package com.example.ecommerceapp;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

//import android.content.Context;
//import org.junit.Test;
//import org.junit.runner.RunWith;

//@RunWith(MockitoJUnitRunner.class)

public class LoginActivityUnitTest {
    @Rule
    //public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRul<LoginActivity>(LoginActivity.class);
    private LoginActivity View;
    private LoginActivity Service;
    private LoginActivity Presenter;

    @Before
    public void setUp() throws Exception {
       // Presenter = new LoginActivity(View, Service);

    }

    //private static final String FAKE_STRING = "Login was successful";

       // @Mock
       // Context mMockContext;

        @Test
        public void  shouldShowErrorMessageWhenUsernameIsEmpty() throws Exception {
        //when(View.getUsername()).thenReturn("");
        //Presenter.onLoginClicked();

       // verify(view).showUsernameError(R.string.username_error);

        }

        @After
        public void tearDown() throws Exception {

        }
        }



