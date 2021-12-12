package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.NOT_FOUND)
public class ReadingActivityNotFoundException extends RuntimeException {
   public ReadingActivityNotFoundException() {super ("Activity not found");}
}
