package com.techelevator.dao;

import com.techelevator.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component

public class JdbcBookDao implements BookDao {

   private JdbcTemplate jdbcTemplate;

   public JdbcBookDao(JdbcTemplate jdbcTemplate) {
       this.jdbcTemplate = jdbcTemplate;
   }

   @Override
    public List<Book> getBookByUsername(String username) {
       List<Book> books = new ArrayList<>();

       String sql = "SELECT book_id, book_isbn, title, author, family_id " +
               "FROM books " +
               "JOIN family ON family.family_id = books.family_id " +
               "JOIN members ON members.family_id = family.family_id " +
               "JOIN users ON members.user_id = users.user_id " +
               "WHERE username = ?;";
       SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
       while (results.next()) {
           Book newBook = mapRowToBook(results);
           books.add(newBook);
       }
       return books;
   }

   @Override
   public Book addBook(Book newBook, String username) {

       String familyIdSql = "SELECT members.family_id FROM members JOIN users ON members.user_id = users.user_id WHERE username = ?;";

       Long familyId = jdbcTemplate.queryForObject(familyIdSql, Long.class, username);

       String sql = "INSERT INTO books (book_isbn, title, author, family_id)" +
               " VALUES ( ?, ?, ?, ?) RETURNING book_id";

       Long newId = jdbcTemplate.queryForObject(sql, Long.class, newBook.getBookIsbn(), newBook.getTitle(), newBook.getAuthor(), familyId);

       return getBook(newId);

   }

    @Override
    public Book getBook(long bookId) {
       Book book = null;
       String sql = "SELECT book_id, book_isbn, title, author, family_id " +
               "FROM books " +
               "WHERE book_id = ?;";
       SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bookId);
       if (results.next()) {
           book = mapRowToBook(results);
       }
       return book;
    }

    private Book mapRowToBook(SqlRowSet rowSet) {
       Book book = new Book();
       book.setAuthor(rowSet.getString("author"));
       book.setBookId(rowSet.getLong("book_id"));
       book.setBookIsbn(rowSet.getString("book_isbn"));
       book.setTitle(rowSet.getString("title"));
       book.setFamilyId(rowSet.getLong("family_id"));
       return book;
    }

}


