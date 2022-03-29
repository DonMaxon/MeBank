package com.example.demo.serializers;

import com.example.demo.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class EmployeeSerializer {
    private static EmployeeSerializer instance = null;

    private EmployeeSerializer() {

    }

    public static EmployeeSerializer getInstance() {
        if (instance == null) {
            instance = new EmployeeSerializer();
        }
        return instance;
    }

    public String serialize(Employee employee) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(employee);
    }

    public static Employee deserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        return mapper.readValue(json, Employee.class);
    }
}
