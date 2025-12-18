package com.reviewanalyzer.exception;

public class JsonParserException extends RuntimeException {
    public JsonParserException(String message) {
        super(message);
    }

    public JsonParserException(String message, RuntimeException e) {
        super(message, e);
    }
}
