package com.josue.jaxrs.validation;

/**
 * Created by Josue on 10/07/2016.
 */
public class SampleMessage {

    private Integer id;
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "SampleMessage{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
