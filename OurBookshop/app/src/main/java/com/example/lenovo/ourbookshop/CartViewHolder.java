package com.example.lenovo.ourbookshop;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
/**
 * This class holds the reference of single product added to the cart.
 * How the details of product will be showed when added to  a cart.
 */
public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView ProductName, ProductPrice, ProductQuantity;
    private ItemClickListner itemClickListner;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        ProductName=itemView.findViewById(R.id.cart_product_name);
        ProductPrice=itemView.findViewById(R.id.cart_product_price);
        ProductQuantity=itemView.findViewById(R.id.cart_product_quantity);
    }

    @Override
    public void onClick(View v)
    {
       itemClickListner.onClick(v, getAdapterPosition(), false);

    }
    /**
     * This makes the items added to a cart clickable.
     * perform add or delete operation on a cart item.
     */
    public void setItemClickListner(ItemClickListner itemClickListner)
    {
        this.itemClickListner=itemClickListner;
    }

}
