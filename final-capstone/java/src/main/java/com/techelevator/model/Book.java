package com.techelevator.model;

public class Book {

    private long bookId;
    private String bookIsbn;
    private String title;
    private String author;
    private long familyId;

    public Book () {}

    public Book(long bookId, String bookIsbn, String title, String author, long familyId) {
        this.bookId = bookId;
        this.bookIsbn = bookIsbn;
        this.title = title;
        this.author = author;
        this.familyId = familyId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
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

    public long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(long familyId) {
        this.familyId = familyId;
    }
}



