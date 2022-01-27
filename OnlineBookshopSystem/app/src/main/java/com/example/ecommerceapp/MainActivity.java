package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;


/**
 * A simple {@link AppCompatActivity} subclass.
 * Activities that contain this AppCompatActivity must implement the
 * to handle interaction events.
 * Use the {@link Intent#Intent} factory method to
 * create an instance of this appcompatactivity.
 */




public class MainActivity extends AppCompatActivity {
    private Button JoinNowButton, LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //onCreate method calling
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing objects JoinNowButton, LoginButton

        JoinNowButton = findViewById(R.id.main_join_now_btn);
        LoginButton = findViewById(R.id.main_login_btn);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent object creation1

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        /**
         * Use this factory method to create a new instance of
         * this method not using the provided parameters.
         *
         * @return nothing any instance of method ValidateProductData.
         */
        JoinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent object creation2

                Intent Intentt = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(Intentt);
            }
        });
    }
}