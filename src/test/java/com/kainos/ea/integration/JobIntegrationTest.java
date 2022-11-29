package com.kainos.ea.integration;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.JobRole;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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

        Assertions.assertTrue(response.size() >0);
    }

    @Test
    void viewJobRoles_shouldReturnAResponseOf200() {
        Response response = APP.client().target("http://localhost:8080/api/viewjobroles")
                .request().get();

        Assertions.assertEquals(response.getStatus(),200 );
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

        Assertions.assertTrue(response.size() >0);
    }
    @Test
    void viewJobCapabilities_shouldReturnAResponseOf200() {
        Response response = APP.client().target("http://localhost:8080/api/viewjobcapabilities")
                .request().get();
                
         Assertions.assertEquals(response.getStatus(),200 );
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

}