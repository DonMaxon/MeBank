package com.example.demo.serializers;

import com.example.demo.entity.Deposit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class DepositSerializer {

    private static DepositSerializer instance = null;

    private DepositSerializer() {

    }

    public static DepositSerializer getInstance() {
        if (instance == null) {
            instance = new DepositSerializer();
        }
        return instance;
    }

    public String serialize(Deposit deposit) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(deposit);
    }

    public static Deposit deserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        return mapper.readValue(json, Deposit.class);
    }
}
