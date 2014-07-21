/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.eap.batch.checkpoint.servlet;

import java.io.IOException;
import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Josue
 */
@WebServlet(name = "BatchServlet", urlPatterns = {"/BatchServlet"})
public class BatchServlet extends HttpServlet {

    JobOperator job = BatchRuntime.getJobOperator();
    private long jobId = -1;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (null != action) {
            switch (action) {
                case "start":
                    jobId = job.start("myJob", new Properties());
                    break;
                case "stop":
                    if (jobId != -1) {
                        job.stop(jobId);
                    }
                    break;
                case "restart":
                    if (jobId != -1) {
                        job.restart(jobId, new Properties());
                    }
                    break;
                case "abandon":
                    if (jobId != -1) {
                        job.abandon(jobId);
                    }
                    break;
                default:
                    break;
            }
        }

        response.getWriter().println(jobId);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
