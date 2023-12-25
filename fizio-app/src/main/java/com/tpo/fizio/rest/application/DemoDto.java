package com.tpo.fizio.rest.application;

/**
 * @author Tadej Delopst
 */
public class DemoDto {
    private String message;

    public DemoDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
