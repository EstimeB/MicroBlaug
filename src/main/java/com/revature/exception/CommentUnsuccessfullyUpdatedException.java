package com.revature.exception;

public class CommentUnsuccessfullyUpdatedException extends RuntimeException {
    public CommentUnsuccessfullyUpdatedException(String message) {
        super(message);
    }
}
