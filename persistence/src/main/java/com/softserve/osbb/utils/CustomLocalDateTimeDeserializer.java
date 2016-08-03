package com.softserve.osbb.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by nazar.dovhyy on 10.07.2016.
 */
public class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDate> {

    private static DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        try {
            String date = jsonParser.getText();

            return LocalDate.parse(date, dateTimeFormatter);

        }catch(IOException e){
            throw new IOException(e.getMessage());
        }
    }
}
