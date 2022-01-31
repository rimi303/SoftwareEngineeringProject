package com.example.lenovo.ourbookshop;
/*
 *This class is to store the details of the book.
 */
public class Book {
    static String title;
    static String price;
    static String url;

    public Book() {
    }

    public Book(String title, String price, String url) {
        this.title = title;
        this.price = price;
        this.url = url;
    }

    public static String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public static String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
