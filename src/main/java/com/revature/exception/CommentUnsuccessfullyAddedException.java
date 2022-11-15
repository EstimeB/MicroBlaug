package com.revature.exception;

public class CommentUnsuccessfullyAddedException extends RuntimeException {
    public CommentUnsuccessfullyAddedException(String message) {
        super(message);
    }
}
