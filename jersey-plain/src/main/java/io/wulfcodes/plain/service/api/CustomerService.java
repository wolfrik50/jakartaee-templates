package io.wulfcodes.plain.service.api;

import java.util.List;
import io.wulfcodes.plain.model.data.CustomerData;

public interface CustomerService {
    List<CustomerData> getCustomers(boolean includeInvoices);
}
