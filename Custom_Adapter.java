package com.example.connectfirebase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

    //we specify the custom ViewHolder which gives us access to our views
    public class Custom_Adapter extends ArrayAdapter<Model> {
    private Activity context;
    // Store a member variable for the data
    private List<Model> datalist;

    // Pass in the data array into the constructor
    public Custom_Adapter(Activity context, List<Model> datalist) {
        super(context, R.layout.sample_layout, datalist);
        this.context = context;
        this.datalist = datalist;
    }

    @NonNull
    @Override

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    // Usually involves inflating a layout from XML and returning the holder
    public View getView(int position, View convertView,  ViewGroup parent) {
        // Inflate the custom layout
        LayoutInflater layoutInflater =context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.sample_layout,null,true);
        Model model=datalist.get(position);
        // Set item views based on your views and data model
        TextView t1=view.findViewById(R.id.tvName);
        TextView t2=view.findViewById(R.id.tvPrice);
        t1.setText("Price = "+model.getBookPrice());

        //Returns view
        return view;
    }
}