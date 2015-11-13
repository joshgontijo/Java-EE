/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jpa.tableperclass.inheritance;

import com.josue.jpa.tableperclass.inheritance.entity.Developer;
import com.josue.jpa.tableperclass.inheritance.entity.Employee;
import com.josue.jpa.tableperclass.inheritance.entity.Manager;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("employee")
@ApplicationScoped
@Transactional
public class EmployeeResource {

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getJson() {
        Employee dev = new Developer("java");
        Employee man = new Manager("datalabb");
        em.persist(dev);
        em.persist(man);
        return em.createQuery("SELECT emp FROM Employee emp", Employee.class).getResultList();
    }
}
