package com.kainos.ea.controller;

import com.kainos.ea.dao.RegisterDao;
import com.kainos.ea.model.User;
import com.kainos.ea.service.RegisterService;

import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("API for Register")
@Path("/api")
public class RegisterController {

   private static RegisterService registerService;

   public RegisterController() {registerService = new RegisterService(new RegisterDao());}

   @POST
   @Path("/register")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response createUser(User user) {
      try {
         return Response.ok(registerService.registerUser(user)).build();
      } catch(SQLException e) {
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
      }
   }

}
