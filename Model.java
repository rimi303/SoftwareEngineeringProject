package com.example.connectfirebase;

import java.util.Comparator;
/**
 * Model class containing Bookprice and BookName
 */

public class Model<p2> {

    //Name of the book
    private String bookName;
    //Price of the book
    private String bookPrice;


    //default constructor
    public Model() {

    }

    //Constructor
    public Model(String bookName,String bookPrice) {
        this.bookName=bookName;
        this.bookPrice = bookPrice;


    }

    //get bookPrice
    public String getBookPrice() {
        return bookPrice;
    }

    //set bookPrice
    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    //get bookName
    public String getBookName() {
        return bookName;
    }

    //set bookName
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /*
    Comparator
    It compares datalist & sort the BookName in alphabetically A_Z format.
     */
    public static Comparator<Model> SortNameAZ = new Comparator<Model>() {
        @Override
        public int compare(Model t1, Model t2) {

            return t1.getBookName().compareTo(t2.getBookName());
        }
    };
    /*
   Comparator
   It compares datalist & sort the BookName in alphabetically Z_A format.
    */
    public static final Comparator<Model> SortNameZA = new Comparator<Model>() {
        @Override
        public int compare(Model t1, Model t2) {

            return t2.getBookName().compareTo(t1.getBookName());
        }
    };


    /*
    Comparator
    It compares Bookprice & sort the BookPrice in ascending order.
     */
    public static Comparator<Model> SortPriceAscending = new Comparator<Model>() {
        @Override
        public int compare(Model t1, Model t2) {

            return t1.getBookPrice().compareTo(t2.getBookPrice());
        }
    };
    /*
    Comparator
    It compares Bookprice & sort the BookPrice in descending order.
     */
    public static final Comparator<Model> SortAgeDescending = new Comparator<Model>() {
        @Override
        public int compare(Model t1, Model t2) {

            return t2.getBookPrice().compareTo(t1.getBookPrice());
        }
    };



}
