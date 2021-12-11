package com.techelevator.controller;


import com.techelevator.dao.BookDao;
import com.techelevator.dao.JdbcBookDao;
import com.techelevator.model.Book;
import com.techelevator.model.BookNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/books")

public class BookController {

    private JdbcBookDao bookDao;

    public BookController(JdbcBookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping(path = "")
    public List<Book> getListOfBooks(@RequestParam String username) throws BookNotFoundException {
        return this.bookDao.getBookByUsername(username);
    }

    @PostMapping (path = "/add")
    public Book addBook(@RequestBody Book newBook, @RequestParam String username) {
        return this.bookDao.addBook(newBook, username);
    }


}
