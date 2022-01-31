package com.example.lenovo.ourbookshop;
//This class is to hold the attributes related to a cart and methods to access the attributes.
public class Cart {
    private String BookId, BookTitle, BookPrice, Discount, Quantity;

    public Cart() {
    }

    public Cart(String bookId, String bookTitle, String bookPrice, String discount, String quantity) {
        BookId = bookId;
        BookTitle = bookTitle;
        BookPrice = bookPrice;
        Discount = discount;
        Quantity = quantity;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }

    public String getBookPrice() {
        return BookPrice;
    }

    public void setBookPrice(String bookPrice) {
        BookPrice = bookPrice;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String discount) {
        Quantity = discount;
    }
}
