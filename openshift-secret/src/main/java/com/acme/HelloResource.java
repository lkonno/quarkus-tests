package com.acme;

import java.util.Optional;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class HelloResource {
    @ConfigProperty(name = "hello.message")
    Optional<String> message;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@QueryParam("name") @DefaultValue("World") String name) {
        if (message.isPresent()) {
            return Response.ok().entity(message.get()).build();
        }

        return Response.status(500).entity("Secret not present").build();
    }
}
