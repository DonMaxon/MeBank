package com.example.demo.serializers;

import com.example.demo.entity.Credit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class CreditSerializer {

    private static CreditSerializer instance = null;

    private CreditSerializer() {

    }

    public static CreditSerializer getInstance() {
        if (instance == null) {
            instance = new CreditSerializer();
        }
        return instance;
    }

    public String serialize(Credit credit) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(credit);
    }

    public static Credit deserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        return mapper.readValue(json, Credit.class);
    }
}
