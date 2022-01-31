package com.example.lenovo.ourbookshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


/**
 * This fragment is responsible for showing details of a book.
 * Also responsible for initial step of order processing.
 */
public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String title, price, url;

    public DetailsFragment() {
        // Required empty public constructor
    }
    public DetailsFragment(String title, String price, String url) {
        this.title = title;
        this.price = price;
        this.url = url;

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ImageView imageview = view.findViewById(R.id.book_image);
        final TextView DetailsTitle = view.findViewById(R.id.details_title);
        final TextView DetailsPrice = view.findViewById(R.id.details_price);
        final ElegantNumberButton CountButton= view.findViewById(R.id.counter_btn);
        Button AddToCart=view.findViewById(R.id.add_to_cart);

        AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddingToCartList();
            }

            private void AddingToCartList() {
                String SaveCurrentDate, SaveCurrentTime;
                Calendar CallForDate= Calendar.getInstance();
                SimpleDateFormat currentDate= new SimpleDateFormat("mm dd, yyyy");
                SaveCurrentDate= currentDate.format(CallForDate.getTime());

                SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
                SaveCurrentTime= currentTime.format(CallForDate.getTime());

                final DataBaseReference CartListRef=FirebaseDatabase.getInstance().getReference().child("Cart List");
                final HashMap<String ,Object> CartMap =new HashMap<>();
                CartMap.put("book Title",DetailsTitle.getText().toString());
                CartMap.put("book Price",DetailsPrice.getText().toString());
                CartMap.put("Date", SaveCurrentDate);
                CartMap.put("Time", SaveCurrentTime);
                CartMap.put("Quantity", CountButton.getNumber());
                CartMap.put("discount", " ");

                CartListRef.child("User View").child(Prevalent.currentOnlineUser.getPhone())
                        .child("Computer Science Books").child(bookId)
                        .updateChildren(CartMap)
                        .addOnCompleteListener(new OnCompleteListener<void> (){
                            public void onComplete(Task<void> task){
                                if(task.isSuccessful()){
                                    CartListRef.child("Admin View").child(Prevalent.currentOnlineUser.getPhone())
                                            .child("Computer Science Books").child(bookId)
                                            .updateChildren(CartMap)
                                            .addOnCompleteListener(new OnCompleteListener<void>(){
                                                public void onComplete(Task<void> task){
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(DetailsFragment.this,"Added to Cart",Toast.LENGTH_SHORT).show();
                                                        Intent intent= new Intent(DetailsFragment.this, RecFragment.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });
                                }

                            }

                });



            }
        });

        DetailsTitle.setText(title);
        DetailsPrice.setText(price);
        Glide.with(getContext()).load(url).into(imageview); //loading image to the imageview with the help of glide library.


        return view;
    }

    /*
    * @method onBackPressed implements..to which fragment user will be rendered on pressing the image.
    */
    public void onBackPressed() {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new DetailsFragment()).addToBackStack(null).commit();

    }
}

