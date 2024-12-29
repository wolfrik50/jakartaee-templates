package io.wulfcodes.plain.rest.resource.v1;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("/v1/sessions")
public class SessionResource {

    @POST
    public Response registerUser() { return null; }

    @POST
    public Response loginUser() { return null; }

    @DELETE
    public Response logoutUser() { return null; }

}
