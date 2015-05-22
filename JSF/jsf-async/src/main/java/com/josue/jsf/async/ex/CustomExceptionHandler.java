/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.async.ex;

import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 *
 * @author jgontijo
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    private static final Logger log = Logger.getLogger(CustomExceptionHandler.class.getName());

    private final ExceptionHandler wrapped;

    public CustomExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {
        //Iterate over all unhandeled exceptions
        Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
        while (i.hasNext()) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
            NavigationHandler nav = facesContext.getApplication().getNavigationHandler();

            //obtain throwable object
            Throwable throwable = context.getException();
            try {
                if (throwable instanceof ViewExpiredException) {
                    ViewExpiredException viewExpiredException = (ViewExpiredException) throwable;
                    NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
                    requestMap.put("exceptionMessage", getRootClause(throwable).getMessage());
                    requestMap.put("viewId", viewExpiredException.getViewId());
                    navigationHandler.handleNavigation(facesContext, null, "/pages/view-expired");
                } else {
                    requestMap.put("exceptionMessage", getRootClause(throwable).getMessage());
                    nav.handleNavigation(facesContext, null, "/pages/error");
                }
                log.log(Level.SEVERE, "ERROR: {0}", throwable.getLocalizedMessage());
                facesContext.renderResponse();
            } finally {
                //after exception is handeled, remove it from queue
                i.remove();
            }
        }
        //let the parent handle the rest
        getWrapped().handle();
    }

    private Throwable getRootClause(Throwable stack) {
        Throwable last = stack;
        while (last.getCause() != null) {
            last = last.getCause();
        }
        return last;
    }
}
