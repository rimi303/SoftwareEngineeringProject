package com.example.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;


/**
 * A simple {@link AppCompatActivity} subclass.
 * Activities that contain this AppCompatActivity must implement the
 * to handle interaction events.
 * Use the {@link Intent#Intent} factory method to
 * create an instance of this appcompatactivity.
 */


public class AdminCategoryActivity extends AppCompatActivity {
    private ImageView Book1, Novel, Novel2, Pencil;
    private ImageView Reading, Quran, Story, Student;
    private ImageView Quran2, Orders, International, International2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        //initializing objects Book1, Novel, Novel2, Pencil, Reading, Quran, Story, Student, Quran2, Orders, International, International2

        Book1 = (ImageView) findViewById(R.id.Book1);
        Novel = (ImageView) findViewById(R.id.Novel);
        Novel2 = (ImageView) findViewById(R.id.Novel2);
        Pencil = (ImageView) findViewById(R.id.Pencil);
        Reading = (ImageView) findViewById(R.id.Reading);
        Quran = (ImageView) findViewById(R.id.Quran);
        Story = (ImageView) findViewById(R.id.Story);
        Student = (ImageView) findViewById(R.id.Student);
        Quran2 = (ImageView) findViewById(R.id.Quran2);
        Orders = (ImageView) findViewById(R.id.Orders);
        International = (ImageView) findViewById(R.id.International);
        International2 = (ImageView) findViewById(R.id.International2);


        /**
         * Use this factory method to create a new instance of
         * this setOnClickListener not using the provided parameters.
         *
         * @return nothing any instance of method OpenGallery.
         */

        Book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Intent object creation1
                Intent Intentt = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                //adding category object0
                Intentt.putExtra("Category", "Book1");
                startActivity(Intentt);
            }
        });

        Novel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Intent object creation2
                Intent Intentt = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                //adding category object1
                Intentt.putExtra("Category", "Novel");
                startActivity(Intentt);
            }
        });

        Novel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Intent object creation3
                Intent Intentt = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);

                //adding category object2
                Intentt.putExtra("Category", "Novel2");
                startActivity(Intentt);
            }
        });

        Pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Intent object creation4
                Intent Intentt = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);

                //adding category object3
                Intentt.putExtra("Category", "Pencil");
                startActivity(Intentt);
            }
        });

        Reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Intent object creation5
                Intent Intentt = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);

                //adding category object4
                Intentt.putExtra("Category", "Reading");
                startActivity(Intentt);
            }
        });

        Quran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Intent object creation6
                Intent Intentt = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);

                //adding category object5
                Intentt.putExtra("Category", "quran");
                startActivity(Intentt);
            }
        });

        Story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Intent object creation7
                Intent Intentt = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);

                //adding category object6
                Intentt.putExtra("Category", "story");
                startActivity(Intentt);
            }
        });

        Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Intent object creation8
                Intent Intentt = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);

                //adding category object7
                Intentt.putExtra("Category", "student");
                startActivity(Intentt);
            }
        });

        Quran2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Intent object creation9
                Intent Intentt = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);

                //adding category object8
                Intentt.putExtra("Category", "quran2");
                startActivity(Intentt);
            }
        });

        Orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Intent object creation10
                Intent Intentt = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);

                //adding category object9
                Intentt.putExtra("Category", "orders");
                startActivity(Intentt);
            }
        });

        International.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Intent object creation11
                Intent Intentt = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);

                //adding category object10
                Intentt.putExtra("Category", "international");
                startActivity(Intentt);
            }
        });

        International2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Intent object creation12
                Intent Intentt = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);

                //adding category object11
                Intentt.putExtra("Category", "international2");
                startActivity(Intentt);
            }
        });
    }
}