package io.wulfcodes.plain.rest;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.gson.JsonGsonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import static org.glassfish.jersey.server.ServerProperties.APPLICATION_NAME;

@ApplicationPath("/api")
public class PlainApp extends ResourceConfig {

    {
        // Register Properties
        property(APPLICATION_NAME, "plain");

        // Register Packages
        packages(true, "io.wulfcodes.plain.rest.resource", "io.wulfcodes.plain.rest.filter");

        // Register Features
        register(JsonGsonFeature.class);
    }

}
