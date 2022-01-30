package com.example.discount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.discount.Controller.IDiscountController;
import com.example.discount.Controller.DiscountController;
import com.example.discount.View.IDiscountView;
public class MainActivity extends AppCompatActivity  implements IDiscountView {
    EditText name ,id;
    Button loginb;
    IDiscountController discountPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     name = (EditText) findViewById(R.id.name);
      id = (EditText)findViewById(R.id.id);
        loginb = (Button) findViewById(R.id.submit);
        discountPresenter = new DiscountController(this);
        loginb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discountPresenter.OnDiscount(name.getText().toString().trim(),id.getText().toString().trim());
            }
        });
    }
    @Override
    public void OnDiscountSuccess(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void OnDiscountError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnCheck(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void OnPosition(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void OnType(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void OnPurchase(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}




