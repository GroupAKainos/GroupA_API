package com.kainos.ea.controller;

import com.kainos.ea.dao.UserDao;
import com.kainos.ea.model.User;
import com.kainos.ea.service.UserService;

import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("API for User Application app")
@Path("/api")
public class UserController {

   private static UserService userService;

   public UserController() {userService = new UserService(new UserDao());}

   @POST
   @Path("/register")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response createUser(User user) {
      try {
         return Response.ok(userService.registerUser(user)).build();
      } catch(SQLException e) {
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
      }
   }

   @POST
   @Path("/login")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response logUser(User user) {
      try {
         return Response.ok(userService.loginUser(user)).build();
      } catch(SQLException e) {
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
      }
   }
}
