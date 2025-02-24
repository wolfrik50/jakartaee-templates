package io.wulfcodes.plain.rest.serdes;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import io.wulfcodes.plain.model.data.CustomerData;
import io.wulfcodes.plain.model.exchange.CustomerResponse;

import static java.nio.charset.StandardCharsets.UTF_8;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationScoped
@Provider
@Log4j2
public class CustomerResponseWriter implements MessageBodyWriter<CustomerResponse> {

    @Inject
    @Named("gson")
    private Gson gsonProcessor;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return CustomerResponse.class.isAssignableFrom(type);
    }

    @Override
    @Produces(APPLICATION_JSON)
    public void writeTo(
        CustomerResponse response,
        Class<?> type,
        Type genericType,
        Annotation[] annotations,
        MediaType mediaType,
        MultivaluedMap<String, Object> httpHeaders,
        OutputStream entityStream
    ) throws IOException, WebApplicationException {

        try (Writer writer = new PrintWriter(entityStream, true, UTF_8)) {
            log.info("Preparing message body from response : {}", response);

            if (response.data() instanceof CustomerData) {
                CustomerData data = (CustomerData)response.data();
                if (StringUtils.isBlank(data.getAddress())) {
                    StringBuilder builder = new StringBuilder();
                    if (StringUtils.isNoneBlank(data.getPlot())) {
                        builder.append(data.getPlot()).append(", ");
                        data.setPlot(null);
                    }

                    if (StringUtils.isNoneBlank(data.getCity())) {
                        builder.append(data.getCity()).append(", ");
                        data.setCity(null);
                    }

                    if (StringUtils.isNoneBlank(data.getState())) {
                        builder.append(data.getState());
                        data.setState(null);
                    }
                    String address = builder.toString();
                    data.setAddress(address.isBlank() ? null : address);
                }
            } else if (response.data() instanceof List<?>) {
                List<CustomerData> dataList = (List<CustomerData>)response.data();
                for (CustomerData data : dataList) {
                    if (StringUtils.isBlank(data.getAddress())) {
                        StringBuilder builder = new StringBuilder();
                        if (StringUtils.isNoneBlank(data.getPlot())) {
                            builder.append(data.getPlot()).append(", ");
                            data.setPlot(null);
                        }

                        if (StringUtils.isNoneBlank(data.getCity())) {
                            builder.append(data.getCity()).append(", ");
                            data.setCity(null);
                        }

                        if (StringUtils.isNoneBlank(data.getState())) {
                            builder.append(data.getState());
                            data.setState(null);
                        }
                        String address = builder.toString();
                        data.setAddress(address.isBlank() ? null : address);
                    }
                }
            }

            writer.write(gsonProcessor.toJson(response));
            writer.flush();
        } catch (Exception ex) {
            log.fatal("Some exception occurred while preparing message body of : {}", response, ex);
        }

    }

}
