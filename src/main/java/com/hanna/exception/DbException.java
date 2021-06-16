package com.hanna.exception;

public class DbException extends RuntimeException {
    public DbException(String message) {
        super("Impossible action with date base: " + message);
    }
}