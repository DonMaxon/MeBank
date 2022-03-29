package com.example.demo.deserializers;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Info;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AdminDeserializer extends StdDeserializer<Admin> {

    public AdminDeserializer() {
        this(null);
    }

    protected AdminDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Admin deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        JsonNode jn = mapper.readTree(jsonParser.readValueAsTree().toString());
        UUID uuid = UUID.fromString(jn.get("id").asText());
        String name = jn.get("name").asText();
        String login = jn.get("login").asText();
        String password = jn.get("password").asText();
        String js = jn.get("employees").toString();
        List<Employee> employees;
        employees = js.equals("") ? new ArrayList<>(): new ArrayList<>(Arrays.asList(mapper.readValue(js, Employee[].class)));
        js = jn.get("infos").toString();
        List<Info> infos;
        infos = js.equals("") ? new ArrayList<>(): new ArrayList<>(Arrays.asList(mapper.readValue(js, Info[].class)));
        return new Admin(uuid, name, login, password, employees, infos);
    }
}
