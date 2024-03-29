package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.ecommerceapp.Model.Users;

//import com.google.firebase.database.FirebaseDatabase;
//com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.ValueEventListener;
//import com.example.ecommerceapp.Model.Users;


/**
 * A simple {@link AppCompatActivity} subclass.
 * Activities that contain this AppCompatActivity must implement the
 * to handle interaction events.
 * Use the {@link Intent#Intent} factory method to
 * create an instance of this appcompatactivity.
 */




public class LoginActivity extends AppCompatActivity {

    public EditText InputPhoneNumber, InputPassword;
    public Button LoginButton;
    public ProgressDialog LoadingBar;
    public TextView AdminLink, NotAdminLink;

    //Parent database name Users

    public String ParentDatabaseName = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initializing objects LoginButton, InputPhoneNumber, InputPassword, LoadingBar, AdminLink, NotAdminLink

        LoginButton = findViewById(R.id.login_btn);
        InputPassword = findViewById(R.id.login_password_input);
        InputPhoneNumber = findViewById(R.id.login_phone_input);
        AdminLink = (TextView) findViewById(R.id.admin_panel_link);
        NotAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);
        LoadingBar = new ProgressDialog(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                //User-define function calling

                LoginUser();
            }

        });

        /**
         * Use this factory method to create a new instance of
         * this method setOnClickListener not using the provided parameters.
         *
         * @return nothing any instance of method ValidateProductData.
         */
        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setText and visibility
                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);

                //Parent databse name Admins
                ParentDatabaseName = "Admins";
            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setText and visibility2
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);

                //Parent databse name Users
                ParentDatabaseName = "Users";

            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this method using the provided parameters.
     *
     * @return nothing of method LoginUser.
     */
    public void LoginUser()
    {
        //initializing Phone and Password
        String Phone = InputPhoneNumber.getText().toString();
        String Password = InputPassword.getText().toString();

        //start the if-else condition
        if(TextUtils.isEmpty(Phone))
        {
            //Toast Creation1
            Toast.makeText(this, "Please give your phone!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Password))
        {
            //Toast creation2
            Toast.makeText(this, "Please give the password!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //The loadingBar creation
            LoadingBar.setTitle("Login Account");
            LoadingBar.setMessage("{Please wait, while we are checking the credentials.");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();

            //User-define function calling again
            AllowAccessToAccount(Phone, Password);
        }

        //end the if-else condition
    }


    // the appcompatactivity initialization parameters, e.g. ParentDatabaseName

    /**
     * Use this factory method to create a new instance of
     * this method using the provided parameters.
     *
     * @param Phone Parameter 1.
     * @param Password Parameter 2.
     * @return A new instance of method AllowAccessToAccount.
     */

    private void AllowAccessToAccount(String Phone, String Password)
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
                                Toast.makeText(LoginActivity.this, "Welcome admin,you are Logged in successfully...", Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();

                                //Intent object creation1
                                Intent Intentt = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                                startActivity(Intentt);
                            }
                            else if(ParentDatabaseName.equals("Users"))
                            {
                                //Toast creation6 and dismiss the loadingBar2
                                Toast.makeText(LoginActivity.this, "Login successful...", Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();

                                //Intent object creation2
                                Intent Intentt = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(Intentt);
                            }
                        }
                        else
                        {
                            //Toast creation6 and dismiss the loadingBar
                            LoadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is in correct", Toast.LENGTH_SHORT).show();
                        }
                        //end the inner if-else condition
                    }
                }
                else
                {
                    //Toast creation7
                    Toast.makeText(LoginActivity.this, "Account with this " + Phone + "number do not exists", Toast.LENGTH_SHORT).show();
                    //Dismiss the loadingBar
                    LoadingBar.dismiss();
                }

                //end the outer if-else condition

            }

            @Override
            public void onCancelled(@NonNull DatabaseError DatabaseErrorR) {
             //should be define the method-body next
            }
        });
    }

    public boolean IsCurrentUserLoggedIn() {
        return true;
    }
}