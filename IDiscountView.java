package com.example.discount.View;

public interface IDiscountView {
    void OnDiscountSuccess(String message);
    void OnDiscountError(String message);
    void OnCheck(String message);
    void OnPosition(String message);
    void OnType(String message);
    void OnPurchase(String message);

}