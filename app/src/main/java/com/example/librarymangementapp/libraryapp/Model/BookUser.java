package com.example.librarymangementapp.libraryapp.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by 001557 on 10/7/2015.
 */
public class BookUser extends RealmObject {
    @PrimaryKey
    private int bookUserId;
    private String title;
    private String author;
    private String imageUrl;
    private boolean isBorrowed;

    public BookUser(int bookUserId,String title, String author, String imageUrl,boolean isBorrowed ) {
        this.bookUserId=bookUserId;
        this.title = title;
        this.author = author;
        this.imageUrl = imageUrl;
        this.isBorrowed=isBorrowed;
    }

    public BookUser(){
        //empty costructor
    }

    public int getBookUserId() {
        return bookUserId;
    }

    public void setBookUserId(int bookUserId) {
        this.bookUserId = bookUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public  boolean getIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }
}