package com.example.lenovo.ourbookshop;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class is responsible for all the activities related to a cart.
 */
public class CartActivity extends AppCompatActivity {
    private RecyclerView CartRecyView;
    private RecyclerView.LayoutManager LayoutManaager;
    private Button ProceedButton;
    private TextView TotalAmount;
    private int CumulativePrice=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        CartRecyView = findViewById(R.id.cart_list);
        CartRecyView.setHasFixedSize(true);
        LayoutManaager = new LinearLayoutManager(this);
        CartRecyView.setLayoutManager(LayoutManaager);
        ProceedButton = findViewById(R.id.proceed_btn);
        TotalAmount = findViewById(R.id.tv_total_price);

        ProceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalAmount.setText("Total Price = " + String.valueOf(CumulativePrice));
               Intent intent = new Intent(CartActivity.this,ConfirmOrderActivity.class); //on clicking proceed button user will be referred to ConfirmOrderActivity and due amount will be mentioned there.
               intent.putExtra("Total Price", String.valueOf(CumulativePrice));
               startActivity(intent);
               finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //reference to the child node "Cart List"
        final DatabaseReference CartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        /**
         * Fetches the data stored in the database.
         * Sets the data using setter method of (Cart)model class.
         */
        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>().setQuery(CartListRef.child("User View").child(Prevalent.currentOnlineUser.getPhone()).child("Computer Science Books"), Cart.class).build();
        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            /**
             * Binds the Viewholder and data together.
             * @param holder Viewholder of single item in cart view.
             * @param position
             * @param model model class
             */
            protected void onBindViewHolder(CartViewHolder holder, int position, final Cart model) {
                holder.ProductQuantity.setText("Quantity =" + model.getQuantity());
                holder.ProductName.setText("Price" + model.getBookTitle() + "/=");
                holder.ProductPrice.setText(model.getBookPrice());

                int oneTypeProductTPrice =((Integer.valueOf(model.getBookPrice())))* Integer.valueOf(model.getQuantity()); //Retrieving Price of a single book from database.
                CumulatedPrice = CumulatedPrice + oneTypeProductTPrice; //Calculating total price of multiple books added to cart.

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence options[] = new CharSequence[]{
                            "Edit",
                            "Remove"
                        };
                        AlertDialog.Builder build = new AlertDialog.Builder(CartActivity.this);
                        build.setTitle("Cart Options: ");
                        build.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /**
                                 * when edit option is chosen user referred to book details fragment.
                                 */
                                if(which==0){
                                    Intent intent =new Intent (CartActivity.this, DetailsFragment.class);
                                    intent.putExtra("bookId",model.getBookId());
                                    startActivity(intent);
                                }
                                /**
                                 * When remove option is chosen database is updated accordingly.
                                 */

                                if(which==1){
                                    CartListRef.child("User View")
                                            .child(Prevalent.currentOnlineUser).getPhone().child("Computer Science Books").child(model.getBookId()).removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>(){
                                                public void onComplete(Task<Void> task){
                                                   if(task.isSuccessful()){
                                                       Toast.makeText(CartActivity.this, "Item removed Successfully", Toast.LENGTH_SHORT).show(); //Informing user through a message.
                                                       Intent intent =new Intent (CartActivity.this, RecFragment.class);
                                                       intent.putExtra("bookId",model.getBookId());
                                                       startActivity(intent);
                                                   }
                                                }
                                            });
                                }
                            }
                        });
                        build.show();
                    }
                });

            }

            /**
             * This Method creates reference to the  model described in cart_items_list(View)
             * @param parent
             * @param viewType
             */
            public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout, parent, false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }


        };

        CartRecyView.setAdapter(adapter);
        adapter.startListening();
    }
}