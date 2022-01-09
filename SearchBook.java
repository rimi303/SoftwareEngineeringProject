package com.example.onlinebookshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchBook extends AppCompatActivity {


    private Button searchButton;
    private EditText searchbook;
    private RecyclerView searchlist;
    private String searchinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        searchbook = findViewById(R.id.searchBook);
        searchButton = findViewById(R.id.searchbutton);
        searchlist = findViewById(R.id.searchlist);
        searchlist.setLayoutManager(new LinearLayoutManager(SearchBook.this));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchinput = inputText().getText().toString();

                onStart();

            }
        });
    }

    protected void onStart() {


        super.onStart();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Books")
        FirebaseRecyclerOptions<books> options = new FirebaseRecyclerOptions.Builder<books>()
                .setQuery(reference.orderByChild("bname"), books.class)
                .build();

        FirebaseRecyclerAdapter<books, booksViewHolder> adapter = new FirebaseRecyclerAdapter<books, booksViewHolder>() {
            protected void onBindViewHolder(booksViewHolder holder, int position) {

            }

            @NonNull
            @Override
            public  booksViewHolder onCreateViewHolder(ViewGroup parent,int view)
            {
               View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.books_items_layout,parent,false);
                bookViewHolder holder=new bookViewHolder(view);
                return holder;
            }
        }
    }
}