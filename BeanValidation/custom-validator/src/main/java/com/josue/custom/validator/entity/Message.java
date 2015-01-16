/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.simple.beanvalidation.entity;

/**
 *
 * @author Josue
 */
public class Message {

    private String className;
    private String fieldName;
    private Object value;
    private String message;

    public Message(String className, String fieldName, Object value, String message) {
        this.className = className;
        this.fieldName = fieldName;
        this.value = value == null ? "null" : value;
        this.message = message;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
