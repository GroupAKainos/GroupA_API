package com.kainos.ea.controller;

import com.kainos.ea.dao.LoginDao;
import com.kainos.ea.dao.RegisterDao;
import com.kainos.ea.model.User;
import com.kainos.ea.service.LoginService;
import com.kainos.ea.service.RegisterService;
import io.swagger.annotations.Api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("API for Login")
@Path("/api")
public class LoginController {
    private static LoginService loginService;

    public LoginController() {loginService = new LoginService(new LoginDao());}

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logUser(User user) {
        try {
            return Response.ok(loginService.loginUser(user)).build();
        } catch(SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
