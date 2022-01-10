package com.example.exchangebook;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mquit;

    private String mAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mquit = findViewById(R.id.quit);
        updateBook();
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mButtonChoice1.getText() == mAnswer) {

                        updateBook();
                        Toast.makeText(MainActivity.this,"choose",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "next", Toast.LENGTH_SHORT).show();
                        updateBook();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "finish", Toast.LENGTH_SHORT).show();

                }
            }
        });


        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mButtonChoice2.getText() == mAnswer) {

                        updateBook();
                        Toast.makeText(MainActivity.this,"choose",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "previous", Toast.LENGTH_SHORT).show();
                        updateBook ();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "finish", Toast.LENGTH_SHORT).show();

                }
            }
        });


        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mButtonChoice3.getText() == mAnswer) {

                        updateBook();
                        Toast.makeText(MainActivity.this,"choose",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "next", Toast.LENGTH_SHORT).show();
                        updateBook();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "finish", Toast.LENGTH_SHORT).show();

                }
                finally{

                }
            }
        });


        mquit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                finish();
            }
        });




    }
    private void updateBook(){

    }

    private void updateBook(int point){

    }
}






