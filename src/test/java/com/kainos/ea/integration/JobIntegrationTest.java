package com.kainos.ea.integration;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.NewRole;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobIntegrationTest {

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getJobRoles_shouldReturnListOfJobRoles() {
        List<JobRole> response = APP.client().target("http://localhost:8080/api/viewjobroles")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void viewJobRoles_shouldReturnAResponseOf200() {
        Response response = APP.client().target("http://localhost:8080/api/viewjobroles")
                .request().get();

        Assertions.assertEquals(response.getStatus(), 200);
    }

    @Test
    void getSpec_shouldReturnExpectedJobRole() {
        String response = APP.client().target("http://localhost:8080/api/specification/1")
                .request()
                .get(String.class);

        Assertions.assertTrue(response.contains("Trainee Software Engineer"));
    }

    @Test
    void getSpec_shouldReturnAResponseOf200() {
        Response response = APP.client().target("http://localhost:8080/api/specification/1")
                .request().get();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void getJobCapabilities_shouldReturnListOfJobCapabilities() {
        List<JobRole> response = APP.client().target("http://localhost:8080/api/viewjobcapabilities")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void viewJobCapabilities_shouldReturnAResponseOf200() {
        Response response = APP.client().target("http://localhost:8080/api/viewjobcapabilities")
                .request().get();

        Assertions.assertEquals(response.getStatus(), 200);
    }

    @Test
    void getResponsibility_shouldReturnExpectedJobRole() {
        String response = APP.client().target("http://localhost:8080/api/responsibility/1")
                .request()
                .get(String.class);

        Assertions.assertTrue(response.contains("jobResponsibility"));
    }

    @Test
    void getResponsibility_shouldReturnAResponseOf200() {
        Response response = APP.client().target("http://localhost:8080/api/responsibility/1")
                .request().get();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void getCompetencey_WithAValidBandLevelShouldReturnListOfCompetencies() {
        List<JobRole> response = APP.client().target("http://localhost:8080/api/viewcompetency/2")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void getCompetencey_WithAValidBandLevelShouldReturnAResponseOf200() {
        Response response = APP.client().target("http://localhost:8080/api/viewcompetency/1")
                .request().get();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void getCompetencey_WithAInValidBandLevelShouldReturnAResponseOf500() {
        Response response = APP.client().target("http://localhost:8080/api/viewcompetency/0")
                .request().get();

        Assertions.assertEquals(500, response.getStatus());
    }

    @Test
    void getFamilyList_ShouldReturnAResponseOf200() {
        Response response = APP.client().target("http://localhost:8080/api/populatefamilylist")
                .request().get();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void getFamilyList_ShouldReturnAList() {
        List<JobRole> response = APP.client().target("http://localhost:8080/api/populatefamilylist")
                .request().get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void getBandList_ShouldReturnAResponseOf200() {
        Response response = APP.client().target("http://localhost:8080/api/populatebandlevelist")
                .request().get();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void getBandList_ShouldReturnAList() {
        List<JobRole> response = APP.client().target("http://localhost:8080/api/populatebandlevelist")
                .request().get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void getCapabilityList_ShouldReturnAResponseOf200() {
        Response response = APP.client().target("http://localhost:8080/api/populatecapabiltylist")
                .request().get();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void getCapabilityList_ShouldReturnAList() {
        List<JobRole> response = APP.client().target("http://localhost:8080/api/populatecapabiltylist")
                .request().get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void postAddValidRole_ShouldReturnRoleRequest() {
        NewRole newrole = new NewRole(
                "This is a test",
                "This is a test",
                "This is also a test",
                1,
                1,
                1
        );

        NewRole result = APP.client().target("http://localhost:8080/api/addnewrole")
                .request()
                .post(Entity.entity(newrole, MediaType.APPLICATION_JSON_TYPE)).readEntity(NewRole.class);

        Assertions.assertEquals(newrole.getJobName(), result.getJobName());
        Assertions.assertEquals(newrole.getJobResponsibility(), result.getJobResponsibility());
        Assertions.assertEquals(newrole.getSpecSummary(), result.getSpecSummary());
        Assertions.assertEquals(newrole.getBandLevelID(), result.getBandLevelID());
    }

    @Test
    void postAddInvalidRole_ShouldReturn400WhenJobNameTooShort() {
        NewRole newrole = new NewRole(
                "This",
                "This is a test",
                "This is also a test",
                1,
                1,
                1
        );

        Response result = APP.client().target("http://localhost:8080/api/addnewrole")
                .request()
                .post(Entity.entity(newrole, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, result.getStatus());
    }

    @Test
    void postAddInvalidRole_ShouldReturn400WhenJobResponsibilityTooShort() {
        NewRole newrole = new NewRole(
                "This is a valid test",
                "Invalid",
                "This is also a test",
                1,
                1,
                1
        );

        Response result = APP.client().target("http://localhost:8080/api/addnewrole")
                .request()
                .post(Entity.entity(newrole, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, result.getStatus());
    }

    @Test
    void postAddInvalidRole_ShouldReturn400WhenSpecificationTooShort() {
        NewRole newrole = new NewRole(
                "This is a valid test",
                "This is a valid test",
                "NAN",
                1,
                1,
                1
        );

        Response result = APP.client().target("http://localhost:8080/api/addnewrole")
                .request()
                .post(Entity.entity(newrole, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, result.getStatus());
    }

    @Test
    void postAddInvalidRole_ShouldReturn400WhenJobFamilyLessThan1() {
        NewRole newrole = new NewRole(
                "This is a valid test",
                "This is a valid test",
                "This is valid",
                -1,
                1,
                1
        );

        Response result = APP.client().target("http://localhost:8080/api/addnewrole")
                .request()
                .post(Entity.entity(newrole, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, result.getStatus());
    }

    @Test
    void postAddInvalidRole_ShouldReturn400WhenBandLevelLessThan1() {
        NewRole newrole = new NewRole(
                "This is a valid test",
                "This is a valid test",
                "This is valid",
                1,
                -1,
                1
        );

        Response result = APP.client().target("http://localhost:8080/api/addnewrole")
                .request()
                .post(Entity.entity(newrole, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, result.getStatus());
    }

    @Test
    void postAddInvalidRole_ShouldReturn400WhenCapabilityLevelLessThan1() {
        NewRole newrole = new NewRole(
                "This is a valid test",
                "This is a valid test",
                "This is valid",
                1,
                1,
                -1
        );

        Response result = APP.client().target("http://localhost:8080/api/addnewrole")
                .request()
                .post(Entity.entity(newrole, MediaType.APPLICATION_JSON_TYPE));

        Assertions.assertEquals(400, result.getStatus());
    }

    // Useful test below, but actually deletes data! - How to automatically rollback any changes?

//    @Test
//    void getDeleteJobs_WithJobIdListShouldDeleteJobs() {
//        String param = "1,2,3";
//        Response response = APP.client().target("http://localhost:8080/api/deletejobroles")
//                .request(MediaType.APPLICATION_JSON)
//                .post(Entity.entity(param, MediaType.APPLICATION_JSON), Response.class);
//        Assertions.assertEquals(200, response.getStatus());
//    }

    @Test
    void getDeleteJobs_BadParamShouldReturnAResponseOf406() {
        String param = null;
        Response response = APP.client().target("http://localhost:8080/api/deletejobroles")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(param, MediaType.APPLICATION_JSON), Response.class);
        Assertions.assertEquals(406, response.getStatus());

    }
}