package com.example.lenovo.ourbookshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
//This class performs necessary functions related to order confirmation.
public class ConfirmOrderActivity extends AppCompatActivity {
    private EditText NameField, NumberField, CityField, AddressField;
    private Button ConfirmOrder;
    private String TotalAmount =" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        TotalAmount=getIntent().getStringExtra("Total Price");
        Toast.makeText(this, "Total Price" + TotalAmount, Toast.LENGTH_SHORT).show();

        ConfirmOrder=findViewById(R.id.proceed_btn);
        NameField=findViewById(R.id.name_field);
        NumberField=findViewById(R.id.phn_num_field);
        CityField=findViewById(R.id.city_field);
        AddressField=findViewById(R.id.address_field);

        ConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               CheckShipmentDetails();
            }
        });

    }

    /**
     * Checks if all necessary fields are filled or not.
     */
    private void CheckShipmentDetails() {
        if (TextUtils.isEmpty(NameField.getText().toString())){
           Toast.makeText(this, "Please Enter your Name",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(NumberField.getText().toString())){
            Toast.makeText(this, "Please Enter Your Number",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(AddressField.getText().toString())){
            Toast.makeText(this, "Please Enter Your Address",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(CityField.getText().toString())){
            Toast.makeText(this, "Please Enter Your City",Toast.LENGTH_SHORT).show();
        }
        else{
            ConfirmOrderFunc();
        }
    }

    private void ConfirmOrderFunc() {
        String SaveCurrentDate,SaveCurrentTime;
        Calendar CallForDate= Calendar.getInstance();
        SimpleDateFormat currentDate= new SimpleDateFormat("mm dd, yyyy");
        SaveCurrentDate= currentDate.format(CallForDate.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
        SaveCurrentTime= currentTime.format(CallForDate.getTime());

        // Reference to the Orders node in database.
        final DatabaseReference OrdersRef=FirebaseDatabase.getInstance.getReference().child("Orders")
                .child(Prevalent.currentOnlineUser.getPhone());
        /**
         * Saving the user given values into a hashmap.
         * Then passing the map to the database.
         */
        HashMap<String,Object> OrderMap=new HashMap<>();
        OrderMap.put("Total Amount",TotalAmount);
        OrderMap.put("Name",NameField.getText().toString());
        OrderMap.put("Phone", NumberField.getText().toString());
        OrderMap.put("Address", AddressField.getText().toString());
        OrderMap.put("City", CityField.getText().toString());
        OrderMap.put("Date", SaveCurrentDate);
        OrderMap.put("Time", SaveCurrentTime);
        OrderMap.put("State", "Not Shipped");
        OrdersRef.updateChildren(OrderMap).addOncompleteListener(new OnCompleteListener<Void>(){
            public void onComplete(Task<Void> task)
            {
                if(task.isSuccessful()){
                   FirebaseDatabase.getInstance().getReference().child("Cart List")
                   .child("User View")
                   .child(Prevalent.currentOnlineUser.getPhone())
                   .removeValue()
                   .addOnCompleteListener(new OnCompleteListener<Void>(){
                       public void onComplete(Task<Void> task){
                          if(task.isSuccessful()) {
                              Toast.makeText(ConfirmOrderActivity.this,"Order Placed Successfully",Toast.LENGTH_SHORT).show();
                              Intent intent= new Intent(ConfirmOrderActivity.this, RecFragment.class); // After Confirming order refer the user to the homepage.
                              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                              startActivity(intent);
                              finish();
                          }
                       }
                   });
                }
            }


        });
    }
}
