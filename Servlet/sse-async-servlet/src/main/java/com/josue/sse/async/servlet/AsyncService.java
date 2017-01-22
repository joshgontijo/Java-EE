/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.sse.async.servlet;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            for (int i = 0; i <= 1000; i++) {
                Thread.sleep(1000);

                String currentTime = new SimpleDateFormat("hh:mm:ss").format(new Date());

                logger.log(Level.INFO, ":: PUSHING {0} ::", currentTime);

                //The response payload must start with 'data:' and end with '\n\n'
                //ref: https://www.html5rocks.com/en/tutorials/eventsource/basics/
                writer.write("data: " + currentTime + "\n\n");
                writer.flush();
            }

            context.complete();

        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(AsyncService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
