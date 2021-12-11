package com.techelevator.controller;

import com.techelevator.dao.JdbcReadingActivityDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/activities")
public class ReadingActivityController {
    private JdbcReadingActivityDao readingActivityDao;
    public ReadingActivityController (JdbcReadingActivityDao readingActivityDao) {
        this.readingActivityDao = readingActivityDao;
    }
}
