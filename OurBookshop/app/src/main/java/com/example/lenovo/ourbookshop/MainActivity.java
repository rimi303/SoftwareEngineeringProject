package com.example.lenovo.ourbookshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*this command will replace the layout @id wrapper with content from RecFragment.java
         *@author Jannatul Mawa_1943
         */
        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new RecFragment()).commit();
    }
}
