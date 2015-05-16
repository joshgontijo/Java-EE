/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.exception.handling;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author jgontijo
 */
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {

    private final ExceptionHandlerFactory parent;

    // this injection handles jsf
    public CustomExceptionHandlerFactory(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    //create your own ExceptionHandler
    @Override
    public ExceptionHandler getExceptionHandler() {
        ExceptionHandler result
                = new CustomExceptionHandler(parent.getExceptionHandler());
        return result;
    }
}
