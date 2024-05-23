package com.luissoy.mongocrudapi.controller;

import com.luissoy.mongocrudapi.bean.CustomPropertiesBean;
import com.luissoy.mongocrudapi.exception.DataIntegrityException;
import com.luissoy.mongocrudapi.exception.DataNotFoundException;
import com.luissoy.mongocrudapi.response.StandardErrorResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handleDataNotFoundException(DataNotFoundException ex) {
        return getResponseEntity(
                ex.getMessage()
                , ex.getClass().getName()
                , ex.getStatus()
        );
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<?> handleDataIntegrityException(DataIntegrityException ex) {
        return getResponseEntity(
                ex.getMessage()
                , ex.getClass().getName()
                , ex.getStatus()
        );
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<?> handleDuplicateKeyException(
            DuplicateKeyException ex
            , WebRequest request
    ) {
        String message = ex.getMessage() == null ?
                CustomPropertiesBean.getProperty("exception.data.duplicate-Key.generic")
                : ex.getMessage();

        return getResponseEntity(
                message
                , ex.getClass().getName()
                , HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(
            Exception ex
            , WebRequest request
    ) {
        return getResponseEntity(
                CustomPropertiesBean.getProperty("exception.message.generic")
                , ex.getClass().getName()
                , HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex
            , @Nullable Object body
            , HttpHeaders headers
            , HttpStatusCode statusCode
            , WebRequest request
    ) {

        return getResponseEntity(
                ex.getMessage()
                , ex.getClass().getName()
                , (HttpStatus) statusCode
        );
    }


    private ResponseEntity<Object> getResponseEntity(
            String message
            , String exceptionClass
            , HttpStatus status
    ) {
        return new ResponseEntity<>(
                new StandardErrorResponse(message)
                , status
        );
    }

}
