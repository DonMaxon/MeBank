package com.example.demo.serializers;

import com.example.demo.entity.Admin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class AdminSerializer {



    /*private static AdminSerializer instance = null;

    private AdminSerializer() {

    }

    public static AdminSerializer getInstance() {
        if (instance == null) {
            instance = new AdminSerializer();
        }
        return instance;
    }*/

    public String serialize(Admin admin) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(admin);
    }

    public Admin deserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        return mapper.readValue(json, Admin.class);
    }

}