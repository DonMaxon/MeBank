package com.example.demo.serializers;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Info;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AdminSerializer {

    private static ObjectMapper mapper;

    AdminSerializer() {
        mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
    }

    public String serialize(Admin admin) throws JsonProcessingException {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(admin);
        System.out.println(json);
        return json;
    }

    public static Admin deserialize(String json) throws JsonProcessingException {
        JsonNode jn = mapper.readTree(json);
        UUID uuid = UUID.fromString(jn.get("id").asText());
        String name = jn.get("name").asText();
        String login = jn.get("login").asText();
        String password = jn.get("password").asText();
        String js = jn.get("employees").asText();
        List<Employee> employees;
        employees = js.equals("") ? new ArrayList<>(): new ArrayList<>(Arrays.asList(mapper.readValue(js, Employee[].class)));
        js = jn.get("infos").asText();
        List<Info> infos;
        infos = js.equals("") ? new ArrayList<>(): new ArrayList<>(Arrays.asList(mapper.readValue(js, Info[].class)));
        return new Admin(uuid, name, login, password, employees, infos);
    }

}
