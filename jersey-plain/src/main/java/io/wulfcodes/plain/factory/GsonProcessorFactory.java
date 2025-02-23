package io.wulfcodes.plain.factory;

import java.time.LocalDateTime;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.wulfcodes.plain.adapter.DateTimeAdapter;

@Dependent
public class GsonProcessorFactory {
    
    @Produces @Named("gson")
    public Gson provide(DateTimeAdapter datetimeAdapter) {
        return new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, datetimeAdapter)
            .setPrettyPrinting()
            .create();
    }
    
    public void dispose(@Disposes Gson gson) {}

}
