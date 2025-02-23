package io.wulfcodes.plain.service.impl;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.configuration2.Configuration;
import io.wulfcodes.plain.datastore.CustomerDatastore;
import io.wulfcodes.plain.model.data.CustomerData;
import io.wulfcodes.plain.model.data.InvoiceData;
import io.wulfcodes.plain.service.api.CustomerService;
import io.wulfcodes.plain.service.api.InvoiceService;

@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

    @Inject
    private Configuration config;

    @Inject
    private CustomerDatastore datastore;

    @Inject
    private InvoiceService invoiceService;

    public List<CustomerData> getCustomers(boolean includeInvoices) {
        List<CustomerData> customers = datastore.getCustomers();
        if (includeInvoices) {
            for (CustomerData customer : customers) {
                List<InvoiceData> invoices = invoiceService.getInvoicesByCustomerId(customer.getCustomerId());
                customer.setInvoices(invoices);
            }
        }
        return customers;
    }

}
