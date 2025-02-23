package io.wulfcodes.plain.adapter;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import com.google.common.annotations.Beta;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.wulfcodes.plain.utils.DateTimeUtils;

public class DateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext context) {
        return new JsonPrimitive(DateTimeUtils.beautifyDateTime(localDateTime));
    }

    @Beta
    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        return context.deserialize(jsonElement, LocalDateTime.class);
    }

}
