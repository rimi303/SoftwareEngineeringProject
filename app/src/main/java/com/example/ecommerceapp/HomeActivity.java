package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.firebase.database.FirebaseDatabase;


public class HomeActivity extends AppCompatActivity {
    private Button LogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LogoutButton = findViewById(R.id.logout_btn);

        LogoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                Intent Intentt = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(Intentt);
            }
        });
    }
}