package io.wulfcodes.plain.rest.resolver;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;
import com.google.gson.Gson;

@ApplicationScoped
@Provider
public class GsonContextResolver implements ContextResolver<Gson> {

    @Inject
    private Gson gson;

    @Override
    public Gson getContext(Class<?> type) {
        return gson;
    }

}
