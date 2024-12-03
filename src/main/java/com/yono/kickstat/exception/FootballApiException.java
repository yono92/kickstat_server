package com.yono.kickstat.exception;

public class FootballApiException extends RuntimeException {
    public FootballApiException(String message) {
        super(message);
    }

    public FootballApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
