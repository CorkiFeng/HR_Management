package com.controller.component;

public class MessageException extends Exception {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageException(String message) {
        super();
        this.message = message;
    }
}
