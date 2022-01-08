package com.example.lenovo.bookshopour;

public class Book {
    String Title, Price, Imgurl;

    public Book() {

    }

    public Book(String title, String price, String imgurl) {
        Title = title;
        Price = price;
        Imgurl = imgurl;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImgurl() {
        return Imgurl;
    }

    public void setImgurl(String imgurl) {
        Imgurl = imgurl;
    }
}
