package com.example.lenovo.ourbookshop;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class MyAdapter extends FirebaseRecyclerAdapter<Book,MyAdapter.MyViewHolder>
{
    View view;
    /**
     * Initialize a (@link RecyclerView Adapter) that listens to a Firebase Query. See
     * (@link FirebaseRecyclerOptions) for configuration options.
     * @param options
     */
    public MyAdapter(FirebaseRecyclerOptions<Book> options){
        super(options);
    }

    protected void onBindViewHolder(@NonNull MyViewHolder Holder, int position, @NonNull Book model)//this method binds the view creted by onCreteViewHolder with data from model class(Book.java).
    {
        Holder.Title.setText(Book.getTitle());
        Holder.Price.setText(Book.getPrice());
        Glide.with(Holder.Image.getContext()).load(Book.getUrl()).into(Holder.Image);

        Holder.Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new DetailsFragment(Book.getTitle(),Book.getPrice(),Book.getUrl())).addToBackStack(null).commit();
            }
        });
    }
    @NonNull
    /*
     *this method creates a view by getting reference from the ViewHolder
     */
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType)
    {
         view=LayoutInflater.from(parent.getContext()).inflate(R.layout.book_preview,parent,false);
        return new MyViewHolder(view);
    }

    public void startListening() {
    }

    public void stopListening() {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView Image;
        TextView Title,Price;
        /*
        * This method holds the reference of the views stored in the book_preview layout
        */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Image=itemView.findViewById(R.id.img);
            Title=itemView.findViewById(R.id.title);
            Price=itemView.findViewById(R.id.price);
        }
    }

}
