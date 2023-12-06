package com.mabaya.task.onlinesponsoredads.converts;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateDeserializer
        extends JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd\\MM\\yyyy");

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String dateAsString = jsonParser.getValueAsString();
        return LocalDateTime.parse(dateAsString, formatter);
    }

    public static LocalDateTime convert(String date){
        return LocalDateTime.parse(date, formatter);
    }
}
