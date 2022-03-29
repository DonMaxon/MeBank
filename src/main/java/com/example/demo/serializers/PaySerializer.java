package com.example.demo.serializers;

import com.example.demo.entity.Pay;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class PaySerializer {
    private static PaySerializer instance = null;

    private PaySerializer() {

    }

    public static PaySerializer getInstance() {
        if (instance == null) {
            instance = new PaySerializer();
        }
        return instance;
    }

    public String serialize(Pay pay) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(pay);
    }

    public static Pay deserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        return mapper.readValue(json, Pay.class);
    }
}
