/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.managed.task;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedExecutors;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("test")
@RequestScoped
public class GenericResource {

    @Resource
    ManagedExecutorService mes;

    @GET
    @Produces("text/plain")

    public String getText() {
        Runnable managedTask = ManagedExecutors.managedTask(new Task(), new TaskListener());
        mes.execute(managedTask);

        return "Managed Thread with listener started";
    }

}
