package com.softserve.osbb.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by nazar.dovhyy on 10.07.2016.
 */
public class CustomLocalDateSerializer extends JsonSerializer<LocalDate> {


    private static DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);


    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(dateTimeFormatter.format(localDate));
    }
}
