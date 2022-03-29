package com.example.demo.serializers;

import com.example.demo.entity.Info;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class InfoSerializer {
    private static InfoSerializer instance = null;

    private InfoSerializer() {

    }

    public static InfoSerializer getInstance() {
        if (instance == null) {
            instance = new InfoSerializer();
        }
        return instance;
    }

    public String serialize(Info info) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(info);
    }

    public static Info deserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        return mapper.readValue(json, Info.class);
    }
}
