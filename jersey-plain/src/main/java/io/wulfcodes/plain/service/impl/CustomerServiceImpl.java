package io.wulfcodes.plain.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.configuration2.Configuration;
import io.wulfcodes.plain.datastore.CustomerDatastore;
import io.wulfcodes.plain.service.api.CustomerService;

@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

    @Inject
    private Configuration config;

    @Inject
    private CustomerDatastore datastore;

    public void doSomething() {
        datastore.createCustomer(null);
    }

}
