package io.wulfcodes.plain.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.configuration2.Configuration;
import io.wulfcodes.plain.service.api.CustomerService;

@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

    @Inject
    private Configuration config;

    public void doSomething() {

    }

}
