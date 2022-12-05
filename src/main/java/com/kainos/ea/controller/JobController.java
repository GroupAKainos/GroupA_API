package com.kainos.ea.controller;

import com.kainos.ea.dao.JobDao;
import com.kainos.ea.exception.DatabaseException;
import com.kainos.ea.exception.NotAValidBandLevelException;
import com.kainos.ea.service.JobService;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.POST;
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
    @Path("/specification/{jobid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecification(@PathParam("jobid") int jobid) {
        try {
            return Response.ok(jobService.getSpecificationJob(jobid)).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/responsibility/{jobid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRes(@PathParam("jobid") int jobid) {
        try {
            return Response.ok(jobService.getResponsibilityJob(jobid)).build();
        } catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/viewjobroles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewJobRoles () {
        try {
            return Response.ok(jobService.viewJobRoles()).build();
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }
    @GET
    @Path("/viewjobcapabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewJobCapabilities () {
        try {
            return Response.ok(jobService.viewJobCapabilities()).build();
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }
    @GET
    @Path("/viewcompetency/{bandlevelID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewCompentency (@PathParam("bandlevelID") int bandID) {
        try {
            return Response.ok(jobService.competency(bandID)).build();
        } catch (DatabaseException | SQLException |  NotAValidBandLevelException e) {
            e.printStackTrace();
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }


    @POST
    @Path(value = "/deletejobroles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteJobRoles (String jobIdList) {
        if (jobIdList == null || !(jobIdList.length() > 0)) {
            return Response.status(HttpStatus.NOT_ACCEPTABLE_406).build();
        }
        try {
            String[] ids = jobIdList.split(",");
            for (String s : ids){
                Integer jobId = Integer.valueOf(s);
                System.out.println("JobId " + jobId);
                Boolean result = jobService.deleteJobRole(jobId);
                if (!result){
                    System.out.println("Failed to delete jobId:" + jobId);
                }
            }
            return Response.ok(true).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

}