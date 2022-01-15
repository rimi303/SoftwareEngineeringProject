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
        }else  if (discountCode == 1){
           discountView.OnDiscountError("Please enter A valid id");
        } else  if (discountCode == 2)
        {
            discountView.OnDiscountSuccess("50% for Eid occation");
        }else  if(discountCode == 3){
            discountView.OnDiscountSuccess("50% for old customer");
        }
        else {
            discountView.OnDiscountSuccess("You got discount on purchase");
        }
    }
}
