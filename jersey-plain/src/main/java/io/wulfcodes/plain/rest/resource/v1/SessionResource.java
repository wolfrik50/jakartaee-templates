package io.wulfcodes.plain.rest.resource.v1;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Path("/v1/sessions")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class SessionResource {

    @POST
    @Path("/sign-up")
    public Response registerUser() { return  Response.ok().build(); }

    @POST
    @Path("/sign-in")
    public Response loginUser() { return Response.ok().build(); }

    @DELETE
    @Path("/sign-out")
    public Response logoutUser() { return Response.ok().build(); }

}
