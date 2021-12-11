package com.techelevator.dao;

import com.techelevator.model.Book;

import java.util.List;

public interface BookDao {

    List<Book> getBookByUsername(String username);

   Book addBook(Book book, String username);

   Book getBook(long bookId);


}
