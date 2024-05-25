package com.luissoy.mongocrudapi.exception;

import com.luissoy.mongocrudapi.bean.CustomPropertiesBean;
import org.springframework.http.HttpStatus;

public class DataNotFoundException extends Exception {
    private final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public DataNotFoundException() {
        super(CustomPropertiesBean.getProperty("exception.data.not-found.generic"));
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public HttpStatus getStatus () {
        return this.STATUS;
    }
}
