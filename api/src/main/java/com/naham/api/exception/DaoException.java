package com.naham.api.exception;

public class DaoException extends RuntimeException {
    public DaoException(Exception exception) {
        super(exception.getMessage(), exception.getCause());
    }
}
