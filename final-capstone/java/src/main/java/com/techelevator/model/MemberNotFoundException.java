package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus (HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException() {super ("Member not found"); }
}
