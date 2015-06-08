/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jsf.composite.children;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Josue
 */
@Named
@RequestScoped
public class ModalController {

    private static final Logger logger = Logger.getLogger(ModalController.class.getName());

    public void confirm() {
        logger.info("CONFIRM");
    }

    public void cancel() {
        logger.info("CANCEL");
    }

    public void closeModal() {
        logger.info("Closing modal");
    }
}
