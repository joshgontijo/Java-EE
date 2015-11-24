/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.sse.async.servlet;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Josue
 */
@WebServlet(name = "AsyncServlet", urlPatterns = {"/AsyncServlet"}, asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AsyncServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream;charset=UTF-8");

        logger.info(":: STARTING ASYNC ::");
        AsyncContext asyncContext = request.startAsync(request, response);

        asyncContext.start(new AsyncService(asyncContext));
        logger.info(":: FINISHED ASYNC ::");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
