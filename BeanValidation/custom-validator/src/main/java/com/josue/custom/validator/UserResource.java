/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.custom.validator;

import com.josue.custom.validator.entity.User;
import com.josue.simple.beanvalidation.entity.Message;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Josue
 */
@Path("users")
public class UserResource {

    /*
     {
     "name" : null,
     "age" : 25,
     "address" : {
     "street" : null,
     "number" : 999999
     },
     "birthdate" : 1421366158790
     }
     */
    @GET
    @Produces("application/json")
    public Response getUser() {
        return Response.status(Response.Status.OK).entity(createUser()).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createUser(User user) {
        List<Message> messages = validate(user);
        return Response.status(Response.Status.OK).entity(messages).build();
    }

    private List<Message> validate(User user) {
        List<Message> errorMessages = new ArrayList<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        for (ConstraintViolation<User> cv : constraintViolations) {
            errorMessages.add(new Message(cv.getLeafBean().getClass().getSimpleName(), cv.getPropertyPath().toString(), cv.getInvalidValue(), cv.getMessage()));
        }

        return errorMessages;
    }

    private static final Logger LOG = Logger.getLogger(UserResource.class.getName());

    private User createUser() {
        User user = new User();
        user.setAge(25);
        user.setName("josue");
        user.setBirthdate(new Date());
        return user;
    }

}
