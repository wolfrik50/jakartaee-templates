package io.wulfcodes.plain.service.impl;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.wulfcodes.plain.datastore.InvoiceDatastore;
import io.wulfcodes.plain.model.data.InvoiceData;
import io.wulfcodes.plain.service.api.InvoiceService;

@ApplicationScoped
public class InvoiceServiceImpl implements InvoiceService {

    @Inject
    private InvoiceDatastore invoiceDatastore;

    @Override
    public List<InvoiceData> getInvoices() {
        return invoiceDatastore.getInvoices();
    }

    @Override
    public List<InvoiceData> getInvoicesByCustomerId(long customerId) {
        return invoiceDatastore.getInvoicesByCustomerId(customerId);
    }
}
