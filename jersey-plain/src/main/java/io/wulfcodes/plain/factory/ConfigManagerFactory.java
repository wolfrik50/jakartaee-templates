package io.wulfcodes.plain.factory;

import java.io.File;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.convert.ListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;

@Dependent
public class ConfigManagerFactory implements Factory<Configuration> {

    @Produces
    @Named("config")
    public Configuration provide() throws ConfigurationException {
        File configFile = new File("src/main/resources/config.properties");

        ListDelimiterHandler delimiterHandler = new DefaultListDelimiterHandler(',');

        PropertiesBuilderParameters propertyParameters = new Parameters()
            .properties()
            .setFile(configFile)
            .setListDelimiterHandler(delimiterHandler)
            .setThrowExceptionOnMissing(true);

        try {
            FileBasedConfigurationBuilder<PropertiesConfiguration> configurationBuilder = new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                .configure(propertyParameters);

            return configurationBuilder.getConfiguration();
        } catch (ConfigurationException ex) {
            throw new ConfigurationException("Error loading configuration", ex);
        }
    }

    public void dispose(@Disposes Configuration config) {
        config.clear();
    }

}
