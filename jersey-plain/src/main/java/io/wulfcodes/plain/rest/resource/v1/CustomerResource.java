package io.wulfcodes.plain.rest.resource.v1;

import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import io.wulfcodes.plain.model.data.CustomerData;
import io.wulfcodes.plain.model.exchange.CustomerResponse;
import io.wulfcodes.plain.service.api.CustomerService;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Path("/v1/customers")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class CustomerResource {

    @Inject
    private CustomerService customerService;

    @GET
    public Response getCustomers(
        @QueryParam("include_invoices")
        @DefaultValue("false")
        Boolean includeInvoices
    ) {
        try {
            List<CustomerData> customers = customerService.getCustomers(includeInvoices.booleanValue());
            if (customers.isEmpty())
                return Response.noContent().entity(CustomerResponse.success("No customers available!")).build();
            return Response.ok(CustomerResponse.success("All customers fetched successfully.", customers)).build();
        } catch (Exception ex) {
            return Response.serverError().entity(CustomerResponse.error("Some internal exception occurred!")).build();
        }

    }

}
