package io.wulfcodes.plain.rest.resource.v1;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

//@RequestScoped
//@Path("/v1/sessions")
//@Consumes(APPLICATION_JSON)
//@Produces(APPLICATION_JSON)
public class SessionResource {

    @POST
    public void registerUser() { return ; }

    @POST
    public void loginUser() { return ; }

    @DELETE
    public void logoutUser() { return ; }

}
