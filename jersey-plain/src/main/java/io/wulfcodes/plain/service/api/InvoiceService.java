package io.wulfcodes.plain.service.api;

import java.util.List;
import io.wulfcodes.plain.model.data.InvoiceData;

public interface InvoiceService {
    List<InvoiceData> getInvoices();

    List<InvoiceData> getInvoicesByCustomerId(long customerId);
}
