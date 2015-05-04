/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.asyncservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.AsyncContext;

/**
 *
 * @author Josue
 */
public class AsyncService implements Runnable {

    private final AsyncContext context;

    public AsyncService(AsyncContext context) {
        this.context = context;
    }

    @Override
    public void run() {
        try {
            PrintWriter writer = context.getResponse().getWriter();
            for (int i = 0; i <= 10; i++) {
                Thread.sleep(1000);
                writer.write("<p>.... PROCESSING, HERE IT'S A PARTIAL RESPONSE " + i * 10 + "%</p>");
                writer.flush();
            }

            writer.write("<p>.... ASYNC PROCESSING FINISHED !!!</p>");
            context.complete();

        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(AsyncService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
