/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.sse.async.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.AsyncContext;

/**
 *
 * @author Josue
 */
public class AsyncService implements Runnable {

    private static final Logger logger = Logger.getLogger(AsyncService.class.getName());

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

                String currentTime = new SimpleDateFormat("hh:mm:ss").format(new Date());

                logger.log(Level.INFO, ":: PUSHING {0} ::", currentTime);

                writer.write("data: " + currentTime + "\n\n"); //double line break is needed... TODO why ?
                writer.flush();
            }

            context.complete();

        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(AsyncService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
