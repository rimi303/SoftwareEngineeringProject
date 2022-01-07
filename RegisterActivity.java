package com.example.onlinebookshopadmin;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText InputName, InputPhoneNumber, InputPassword;
    private ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        CreateAccountButton = findViewById(R.id.register_button);
        InputName = findViewById(R.id.register_username_input);
        InputPassword = findViewById(R.id.register_password_input);
        InputPhoneNumber = findViewById(R.id.register_phone_input);
        LoadingBar = new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });

    }

    private void CreateAccount()
    {
        String Name = InputName.getText().toString();
        String Phone = InputPhoneNumber.getText().toString();
        String Password = InputPassword.getText().toString();

        if(TextUtils.isEmpty(Name))
        {
            Toast.makeText(this, "Please write your name!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Phone))
        {
            Toast.makeText(this, "Please give your phone!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Password))
        {
            Toast.makeText(this, "Please give the password!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            LoadingBar.setTitle("Create Account");
            LoadingBar.setMessage("{Please wait, while we are checking the credentials.");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();
            ValidatePhoneNumber(Name, Phone, Password);
        }
    }


    private void ValidatePhoneNumber(String name, String phone, String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(!(dataSnapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", password);
                    userdataMap.put("name", name);

                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                public void onComplete(Task<Void> task)
                                {
                                  if (task.isSuccessful())
                                  {
                                      Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created successfully", Toast.LENGTH_SHORT).show();
                                      LoadingBar.dismiss();

                                      Intent Intentt = new Intent(RegisterActivity.this, LoginActivity.class);
                                      startActivity(Intentt);
                                  }
                                  else
                                  {
                                      LoadingBar.dismiss();
                                      Toast.makeText(RegisterActivity.this, "Error, please try again!", Toast.LENGTH_SHORT).show();
                                  }
                                }
                            });

                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "This " + phone + "already exists!", Toast.LENGTH_SHORT).show();
                    LoadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Please try again using different phone number", Toast.LENGTH_SHORT).show();

                    Intent Intentt = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(Intentt);
                }
            }

            public void onCancelled(DatabaseError DatabaseErrorR) {

            }
        });
    }
}