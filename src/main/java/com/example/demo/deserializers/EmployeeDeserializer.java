package com.example.demo.deserializers;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Client;
import com.example.demo.entity.Employee;
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

public class EmployeeDeserializer extends StdDeserializer<Employee> {

    public EmployeeDeserializer() {
        this(null);
    }

    protected EmployeeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Employee deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        JsonNode jn = mapper.readTree(jsonParser.readValueAsTree().toString());
        UUID uuid = UUID.fromString(jn.get("id").asText());
        String name = jn.get("name").asText();
        String login = jn.get("login").asText();
        String password = jn.get("password").asText();
        //Admin admin = AllRepository.findAdminByID(UUID.fromString(jn.get("adminID").asText()));
        Admin admin = new Admin(UUID.fromString(jn.get("adminID").asText()));
        String js = jn.get("clients").toString();
        List<Client> clients;
        clients = js.equals("") ? new ArrayList<>(): Arrays.asList(mapper.readValue(js, Client[].class));
        return new Employee(uuid, name, login, password, admin, clients);
    }
}
