package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus (HttpStatus.NOT_FOUND)
public class FamilyNotFoundException extends RuntimeException {

    public FamilyNotFoundException() {super ("Family not found"); }
}
