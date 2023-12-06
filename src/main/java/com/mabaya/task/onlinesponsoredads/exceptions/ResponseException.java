package com.mabaya.task.onlinesponsoredads.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResponseException extends ResponseStatusException {
    public ResponseException(Exception e, String funcName) {
        super(
                HttpStatus.BAD_REQUEST,
                e instanceof OnlineSponsoredAdsException ?
                        e.getMessage() : (funcName + "failed"),
                e);
    }
}
