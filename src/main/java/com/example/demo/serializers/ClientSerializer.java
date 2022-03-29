package com.example.demo.serializers;

import com.example.demo.entity.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class ClientSerializer{

    private static ClientSerializer instance = null;

    private ClientSerializer() {

    }

    public static ClientSerializer getInstance() {
        if (instance == null) {
            instance = new ClientSerializer();
        }
        return instance;
    }

    public String serialize(Client client) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(client);
    }

    public static Client deserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        return mapper.readValue(json, Client.class);
    }
}
