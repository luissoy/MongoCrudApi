package com.luissoy.mongocrudapi.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardErrorResponse {
    private final String details;

    public StandardErrorResponse(String details) {
        this.details = details;
    }
}
