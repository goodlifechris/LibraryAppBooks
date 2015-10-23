package com.example.librarymangementapp.libraryapp.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by 001557 on 9/30/2015.
 */
public class Book extends RealmObject {

    @PrimaryKey
    private int bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookDateBought;
    private String bookCondition;
    private String bookURL;
    private String bookImageFileName;
    private boolean isBorrowed;



    public Book(){
        //empty costructor
    }


    public Book(int bookId,String bookTitle,String bookAuthor,String bookDateBought,String bookCondition,String bookURL,String bookImageFileName,boolean isBorrowed){
        this.bookId=bookId;
        this.bookTitle=bookTitle;
        this.bookAuthor=bookAuthor;
        this.bookDateBought=bookDateBought;
        this.bookCondition=bookCondition;
        this.bookURL=bookURL;
        this.bookImageFileName = bookImageFileName;
        this.isBorrowed=isBorrowed;



    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookDateBought() {
        return bookDateBought;
    }

    public void setBookDateBought(String bookDateBought) {
        this.bookDateBought = bookDateBought;
    }

    public String getBookCondition() {
        return bookCondition;
    }

    public void setBookCondition(String bookCondition) {
        this.bookCondition = bookCondition;
    }

    public String getBookURL() {
        return bookURL;
    }

    public void setBookURL(String bookURL) {
        this.bookURL = bookURL;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookImageFileName() {
        return bookImageFileName;
    }

    public void setBookImageFileName(String bookImageFileName) {
        this.bookImageFileName = bookImageFileName;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }
}
