package com.kainos.ea.integration;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.User;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class UserIntegrationTest {

    static final DropwizardAppExtension<WebServiceConfiguration> APP = new DropwizardAppExtension<>(
            WebServiceApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void register_shouldReturnError500_whenEmailisNull() {
        User user = new User(
                null,
                "Password!",
                "Admin",
                "John",
                "Doe"
        );

        Response response = APP.client().target("http://localhost:8080/api/register")
                .request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));



        Assertions.assertEquals( 500, response.getStatus());
    }

    @Test
    void register_shouldReturnError500_whenPasswordIsNull() {
        User user = new User(
                "Something@anything.com",
                null,
                "Admin",
                "John",
                "Doe"
        );

        Response response = APP.client().target("http://localhost:8080/api/register")
                .request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));



        Assertions.assertEquals( 500, response.getStatus());
    }

    @Test
    void login_shouldReturnError500_whenEmailisNull() {
        User user = new User(
                null,
                "Password!",
                "Admin",
                "John",
                "Doe"
        );

        Response response = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));



        Assertions.assertEquals( 500, response.getStatus());
    }

    @Test
    void login_shouldReturnError500_whenPasswordIsNull() {
        User user = new User(
                "Something@anything.com",
                null,
                "Admin",
                "John",
                "Doe"
        );

        Response response = APP.client().target("http://localhost:8080/api/login")
                .request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));



        Assertions.assertEquals( 500, response.getStatus());
    }
}
