package com.example.connectfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoadActivity extends AppCompatActivity {
    private ListView lview;
    DatabaseReference databaseReference;
    private List<Model>dataList;
    private Custom_Adapter custom_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        databaseReference= FirebaseDatabase.getInstance().getReference("Books");
        dataList=new ArrayList<>();
        custom_adapter=new Custom_Adapter(LoadActivity.this,dataList);
        lview=findViewById(R.id.lview);
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {

                dataList.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Model model=dataSnapshot1.getValue(Model.class);
                    dataList.add(model);
                }
                lview.setAdapter(custom_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}