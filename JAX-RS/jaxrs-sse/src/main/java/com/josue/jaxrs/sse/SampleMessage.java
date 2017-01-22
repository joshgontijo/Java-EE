package com.josue.jaxrs.sse;

import java.util.Date;

/**
 * Created by Josue on 22/01/2017.
 */
public class SampleMessage {

    private Date date;
    private String message;

    public SampleMessage() {
    }

    public SampleMessage(String message) {
        this.date = new Date();
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "\"date\":\"" + (date == null ? "null" : date) + "\", " +
                "\"message\":" + (message == null ? "null" : "\"" + message + "\"") +
                "}";
    }
}
