package io.wulfcodes.plain.rest.resource.v1;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import io.wulfcodes.plain.service.api.CustomerService;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

@RequestScoped
@Path("/v1/customers")
//@Consumes(APPLICATION_JSON)
@Produces(TEXT_PLAIN)
public class CustomerResource {

    @Inject
    private CustomerService customerService;

    @GET
    public String hello() {
        customerService.doSomething();
        return "Hello";
    }

}
