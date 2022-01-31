package com.example.ecommerceapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.ecommerceapp.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
 * A simple {@link IntegrationTest} subclass for unit testing.
 * Activities that contain this LoginActivityJUnitTest must implement the
 * to handle interaction events.
 * Use the {@link Intent#Intent} factory method to testLogin()
 * create no instance of this LoginActivityJUnitTest.
 */

@RunWith(AndroidJUnit4.class)

public class IntegrationTest<IntegrationModuleTest> {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    public LoginActivity AdminLoginActivity;
    LoginActivity obj = new LoginActivity();
    public String ParentDatabaseName = "Users";

    public EditText InputPhoneNumber, InputPassword;
    public Button LoginButton;
    public ProgressDialog LoadingBar;
    public TextView AdminLink, NotAdminLink;
    String Phone = InputPhoneNumber.getText().toString();
    String Password = InputPassword.getText().toString();
    private Object String;
    //obj.AllowAccessToAccount(Phone, Password);

    @Before
    public void setUp() throws Exception {
        AdminLoginActivity = mActivityTestRule.getActivity();
    }

    /**
     * Use this factory method to test a new activity of LoginActivity
     * this method using the provided  no parameter.
     *
     * @return nothing of method testLogin.
     */
    @Test
    public void testLogin() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                EditText Phone = AdminLoginActivity.findViewById(R.id.login_phone_input);
                EditText Password = AdminLoginActivity.findViewById(R.id.login_password_input);
                Phone.setText("Phone");
                Password.setText("Password");
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);

                //Parent databse name Users
                ParentDatabaseName = "Users";
                Button LoginButton = AdminLoginActivity.findViewById(R.id.login_btn);
                LoginButton.performClick();
                assertTrue(AdminLoginActivity.IsCurrentUserLoggedIn());
            }
        });

       // private void AllowAccessToAccount(String Phone, String Password)
        {
            final DatabaseReference RootRef;
            //object initialization
            RootRef = FirebaseDatabase.getInstance().getReference();

            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot DataSnapshott)
                {
                    //start the outer if-else condition
                    if (DataSnapshott.child(ParentDatabaseName).child(Phone).exists())
                    {
                        //users object creation
                        Users UsersData = DataSnapshott.child(ParentDatabaseName).child(Phone).getValue(Users.class);

                        if (UsersData.getPhone().equals(Phone))
                        {
                            //start the inner if-else condition
                            if (UsersData.getPassword().equals(Password))
                            {
                                if(ParentDatabaseName.equals("Admins"))
                                {
                                    //Toast creation6 and dismiss the loadingBar3
                                    //Toast.makeText(LoginActivity.this, "Welcome admin,you are Logged in successfully...", Toast.LENGTH_SHORT).show();
                                    LoadingBar.dismiss();

                                    //Intent object creation1
                                    //Intent Intentt = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                                    //startActivity(Intentt);
                                }
                                else if(ParentDatabaseName.equals("Users"))
                                {
                                    //Toast creation6 and dismiss the loadingBar2
                                    //Toast.makeText(LoginActivity.this, "Login successful...", Toast.LENGTH_SHORT).show();
                                    LoadingBar.dismiss();
                                }
                            }
                            else
                            {
                                //Toast creation6 and dismiss the loadingBar
                                LoadingBar.dismiss();
                                //Toast.makeText(LoginActivity.class, "Password is in correct", Toast.LENGTH_SHORT).show();
                            }
                            //end the inner if-else condition
                        }
                    }
                    else
                    {
                        //Toast creation7
                        //Toast.makeText(Intent.this, "Account with this " + "This" + "number do not exists", Toast.LENGTH_SHORT).show();
                        //Dismiss the loadingBar
                        LoadingBar.dismiss();
                    }

                    //end the outer if-else condition
    }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    //Code here...
                }

                private void startActivity(Intent intentt) {
                }

                @After
    public void tearDown() throws Exception {
        AdminLoginActivity = null;
    }


    /* public class IntegrationTest ActivityIntegrationTestCase<MainActivity>{
       public IntegrationTest() {
        super(MainActivity.class);
        }

    public void SetUp() throws Exception {
     super.SetUp();
        }

    //public void TestEditText()
        {
            EditText et = (EditText) getActivity().findViewById(R.id.editText);
            assertNull(et);

        }

     //public void TestButton()
        {

        Button button = (Button) getActivity().findViewById(R.id.button);
        assertNull(button);

        }

     //@Override
     public void TearDown() throws Exception {
        super.TearDown();
        }

      } */
            });
        }
    }
}

