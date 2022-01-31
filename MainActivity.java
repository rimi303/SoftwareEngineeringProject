package com.example.connectfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*



 */

public class MainActivity extends AppCompatActivity {


    //Button
    private Button btn1,btn2;
    //The input field where the user enters bookname,bookprice
    private EditText edtPrice,edtName;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase
        databaseReference= FirebaseDatabase.getInstance().getReference("Bookprice");
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        // Shortcuts to input fields.
        edtName=findViewById(R.id.edtName);

        edtPrice=findViewById(R.id.edtPrice);

        //By clicking Button it will go to another activity
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LoadActivity.class);
                startActivity(intent);
            }
        });

        //By clicking Button it will go to another activity
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedata();
            }


        });
    }

    //Function that stores data in the firebase
    private void savedata() {

        String bookPrice,bookName;
        bookName=edtName.getText().toString();
        bookPrice=edtPrice.getText().toString();

        String key=databaseReference.push().getKey();
        Model sortData=new Model(bookName,bookPrice);
        databaseReference.child(key).setValue(sortData);
        //Toast method that displayed that the value is added
        Toast.makeText(getApplicationContext(),"value is added",Toast.LENGTH_LONG).show();
        //Set null value
        edtPrice.setText("");
        edtName.setText("");


    }

}
