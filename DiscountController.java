package com.example.discount.Controller;

import com.example.discount.Model.User;

import com.example.discount.View.IDiscountView;

public class DiscountController implements IDiscountController {
    IDiscountView discountView;
    public DiscountController(IDiscountView discountView) {
        this.discountView = discountView;
    }
    @Override
    public void OnDiscount(String email, String password) {
        User user = new User(email,password);
        int discountCode = user.isValid();
        if(discountCode == 0)
        {
           discountView.OnDiscountError("Please enter Your Name");
        }
        else  if (discountCode == 1){
           discountView.OnDiscountError("Please enter A valid id");
        }
        else  if (discountCode == 2)
        {
            discountView.OnDiscountSuccess("20% for 2nd time purchase");
        }
        else  if(discountCode == 3){
            discountView.OnCheck("35% for old customer");
        }
        else  if (discountCode == 4)
        {
            discountView.OnType("10% for new customer");
        }
        else  if(discountCode == 5){
            discountView.OnPurchase("35% for Eid occation");
        }
        else {
            discountView.OnDiscountSuccess("You got discount on purchase");
        }
    }
}
