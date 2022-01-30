package com.example.ecommerceapp;

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

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import java.util.HashMap;


/**
 * A simple {@link AppCompatActivity} subclass.
 * Activities that contain this AppCompatActivity must implement the
 * to handle interaction events.
 * Use the {@link Intent#Intent} factory method to
 * create an instance of this appcompatactivity.
 */


public class RegisterActivity extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText InputName, InputPhoneNumber, InputPassword;
    private ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //onCreate method calling
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing objects CreateAccountButton, InputName, InputPhoneNumber, InputPassword, LoadingBar

        CreateAccountButton = findViewById(R.id.register_button);
        InputName = findViewById(R.id.register_username_input);
        InputPassword = findViewById(R.id.register_password_input);
        InputPhoneNumber = findViewById(R.id.register_phone_input);
        LoadingBar = new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User-define function calling
                CreateAccount();
            }
        });

    }

    /**
     * Use this factory method to create a new instance of
     * this method CreateAccount not using the provided parameters.
     *
     * @return nothing any instance of method ValidateProductData.
     */

    private void CreateAccount()
    {
        //initializing variable values Name, Phone, Password

        String Name = InputName.getText().toString();
        String Phone = InputPhoneNumber.getText().toString();
        String Password = InputPassword.getText().toString();

        if(TextUtils.isEmpty(Name))
        {
            //Toast creation1
            Toast.makeText(this, "Please write your name!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Phone))
        {
            //Toast creation2
            Toast.makeText(this, "Please give your phone!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Password))
        {
            //Toast creation3
            Toast.makeText(this, "Please give the password!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //LoadingBar creation1
            LoadingBar.setTitle("Create Account");
            LoadingBar.setMessage("{Please wait, while we are checking the credentials.");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();

            //user-define fubnction calling
            ValidatePhoneNumber(Name, Phone, Password);
        }
    }

    // the appcompatactivity initialization parameters, e.g. ParentDatabaseName

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param Name Parameter 1.
     * @param Phone Parameter 2.
     * @param Password Parameter 3.
     * @return A new instance of method ValidatePhoneNumber.
     */
    private void ValidatePhoneNumber(String Name, String Phone, String Password)
    {
        final DatabaseReference RootRef;

        //initializing object RootRef

        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                //start the outer if-else condition
                if(!(dataSnapshot.child("Users").child(Phone).exists()))
                {
                    //using hashMap
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", Phone);
                    userdataMap.put("password", Password);
                    userdataMap.put("name", Name);

                    RootRef.child("Users").child(Phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                public void onComplete(Task<Void> task)
                                {
                                    // again start the inner if-else condition
                                    if (task.isSuccessful())
                                    {
                                        //Toast creation5
                                        Toast.makeText(RegisterActivity.this, "Congratulations, your account has been created successfully", Toast.LENGTH_SHORT).show();
                                        LoadingBar.dismiss();

                                        //Intent object creation
                                        Intent Intentt = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(Intentt);
                                    }
                                    else
                                    {
                                        //dismiss the loadingBar
                                        LoadingBar.dismiss();
                                        //and Toast creation6
                                        Toast.makeText(RegisterActivity.this, "Error, please try again!", Toast.LENGTH_SHORT).show();
                                    }
                                    //end the inner if-else condition
                                }
                            });

                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "This " + Phone + " already exists!", Toast.LENGTH_SHORT).show();
                    //Toast creation and lodingBar dismiss
                    LoadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Please try again using different phone number", Toast.LENGTH_SHORT).show();

                    //Intent object creation
                    Intent Intentt = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(Intentt);
                }
                //finally end the outer if-else condition
            }

            public void onCancelled(DatabaseError DatabaseErrorR) {
                //should be define the method body next

            }
        });
    }
}