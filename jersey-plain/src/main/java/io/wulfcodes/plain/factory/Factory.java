package io.wulfcodes.plain.factory;

import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;

public interface Factory<T> {
    @Produces T provide() throws Exception;
    void dispose(@Disposes T instance);
}
