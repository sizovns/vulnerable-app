package com.naham.api.exception;

import com.naham.api.model.dto.response.ErrorResponse;
import com.naham.api.model.dto.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    public ValidationErrorResponse validationFailed(Exception e) {
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        List<ValidationErrorResponse.Item> collect = new ArrayList<>();

        if (e instanceof MethodArgumentNotValidException) {
            collect.addAll(((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors()
                    .stream()
                    .map(err -> new ValidationErrorResponse.Item(err.getField(), err.getCode(), err.getDefaultMessage()))
                    .toList());
        } else if (e instanceof HttpMessageNotReadableException) {
            validationErrorResponse.setError(e.getMessage());
        }
        validationErrorResponse.setFields(collect);
        return validationErrorResponse;
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({DaoException.class})
    @ResponseBody
    public ErrorResponse daoOperationFailed(DaoException e) {
        String errorMessage = "Operation failed: " + e.getCause().getClass() + " with message: " + e.getMessage();
        return new ErrorResponse(errorMessage);
    }
}
