package com.kainos.ea.controller;

import com.kainos.ea.dao.JobDao;
import com.kainos.ea.exception.DatabaseException;
import com.kainos.ea.service.JobService;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


@Api("API for Job Application app")
@Path("/api")
public class JobController {

    private static JobService jobService;

    public JobController(){
        jobService = new JobService(new JobDao());
    }

    @GET
    @Path("/specification/{jobName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecification(@PathParam("jobName") String name) {
        try {
            return Response.ok(jobService.getSpecificationJob(name)).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/viewjobcapabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewJobCapabilities () {
        try {
            return Response.ok(jobService.viewJobCapabilities()).build();
        } catch (DatabaseException e) {
            e.printStackTrace();
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }catch (SQLException e){
            e.printStackTrace();
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

    @GET
    @Path("/viewjobroles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewJobRoles () {
        try {
            return Response.ok(jobService.viewJobRoles()).build();
        } catch (DatabaseException e) {
            e.printStackTrace();
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }catch (SQLException e){
            e.printStackTrace();
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }
    @GET
    @Path("/print/{msg}")
    @Produces("text/html")
    public String getMsg(@PathParam("msg") String message){
        return "Hello from a RESTful Web service: " + message;
    }
}