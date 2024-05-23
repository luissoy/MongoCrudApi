package com.luissoy.mongocrudapi.exception;

import com.luissoy.mongocrudapi.bean.CustomPropertiesBean;
import org.springframework.http.HttpStatus;

public class DataIntegrityException extends Exception {
    private final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public DataIntegrityException() {
        super(CustomPropertiesBean.getProperty("exception.data.integrity.generic"));
    }

    public DataIntegrityException(String message) {
        super(message);
    }

    public HttpStatus getStatus () {
        return this.STATUS;
    }
}
