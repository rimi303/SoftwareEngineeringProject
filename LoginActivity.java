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

public class LoginActivity extends AppCompatActivity {
    private EditText InputPhoneNumber, InputPassword;
    private Button LoginButton;
    private ProgressDialog LoadingBar;
    private TextView AdminLink, NotAdminLink;

    private String ParentDatabaseName = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        LoginButton = findViewById(R.id.login_btn);
        InputPassword = findViewById(R.id.login_password_input);
        InputPassword = findViewById(R.id.register_password_input);
        InputPhoneNumber = findViewById(R.id.login_phone_input);
        AdminLink = (TextView) findViewById(R.id.admin_panel_link);
        NotAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);
        LoadingBar = new ProgressDialog(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {

                LoginUser();
            }

        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                ParentDatabaseName = "Admins";
            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                ParentDatabaseName = "Users";

            }
        });
    }

    private void LoginUser()
    {
        String Phone = InputPhoneNumber.getText().toString();
        String Password = InputPassword.getText().toString();

        if(TextUtils.isEmpty(Phone))
        {
            Toast.makeText(this, "Please give your phone!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Password))
        {
            Toast.makeText(this, "Please give the password!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            LoadingBar.setTitle("Login Account");
            LoadingBar.setMessage("{Please wait, while we are checking the credentials.");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();

            AllowAccessToAccount(Phone, Password);
        }
    }

    private void AllowAccessToAccount(String Phone, String Password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot DataSnapshott)
            {
                if (DataSnapshott.child(ParentDatabaseName).child(Phone).exists())
                {
                    Users UsersData = DataSnapshott.child(ParentDatabaseName).child(Phone).getValue(Users.class);

                    if (UsersData.getPhone().equals(Phone))
                    {
                        if (UsersData.getPassword().equals(Password))
                        {
                            if(ParentDatabaseName.equals("Admins"))
                            {
                                Toast.makeText(LoginActivity.this, "Welcome admin,you are Logged in successfully...", Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();

                                Intent Intentt = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                                startActivity(Intentt);
                            }
                            else if(ParentDatabaseName.equals("Users"))
                            {
                                Toast.makeText(LoginActivity.this, "Login successful...", Toast.LENGTH_SHORT).show();
                                LoadingBar.dismiss();

                                Intent Intentt = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(Intentt);
                            }
                        }
                        else
                        {
                            LoadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is in correct", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Account with this " + Phone + "number do not exists", Toast.LENGTH_SHORT).show();
                    LoadingBar.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError DatabaseErrorR) {

            }
        });
    }
}