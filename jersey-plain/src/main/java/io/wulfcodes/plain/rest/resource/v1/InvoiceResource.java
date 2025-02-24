package io.wulfcodes.plain.rest.resource.v1;

import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import io.wulfcodes.plain.model.data.InvoiceData;
import io.wulfcodes.plain.model.exchange.InvoiceResponse;
import io.wulfcodes.plain.service.api.InvoiceService;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Path("/v1/invoices")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class InvoiceResource {

    @Inject
    private InvoiceService invoiceService;

    @GET
    public Response getInvoices() {
        try {
            List<InvoiceData> invoices = invoiceService.getInvoices();
            if (invoices.isEmpty())
                return Response.noContent().entity(InvoiceResponse.success("No invoices available!")).build();
            return Response.ok(InvoiceResponse.success("All invoices fetched successfully.", invoices)).build();
        } catch (Exception ex) {
            return Response.serverError().entity(InvoiceResponse.error("Some internal exception occurred!")).build();
        }

    }
    
}
