package com.example.demo.deserializers;

import com.example.demo.AllRepository;
import com.example.demo.entity.Client;
import com.example.demo.entity.Credit;
import com.example.demo.entity.Deposit;
import com.example.demo.entity.Employee;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClientDeserializer extends StdDeserializer<Client> {

    public ClientDeserializer() {
        this(null);
    }

    protected ClientDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Client deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        JsonNode jn = mapper.readTree(jsonParser.readValueAsTree().toString());
        UUID uuid = UUID.fromString(jn.get("id").asText());
        String name = jn.get("name").asText();
        String login = jn.get("login").asText();
        String password = jn.get("password").asText();
        String passportSeries = jn.get("passportSeries").asText();
        String passportNumber = jn.get("passportNumber").asText();
        String passportIssueBy = jn.get("passportIssueBy").asText();
        Date passportIssueDate = null;
        try {
            passportIssueDate = new SimpleDateFormat("dd-MM-yyyy").parse(jn.get("passportIssueDate").asText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String phone = jn.get("phone").asText();
        Date creatingDate = null;
        try {
            creatingDate = new SimpleDateFormat("dd-MM-yyyy").parse(jn.get("creatingDate").asText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Employee employee = AllRepository.findEmployeeByID(UUID.fromString(jn.get("employeeID").asText()));
        String js = jn.get("credits").toString();
        List<Credit> credits;
        credits = js.equals("") ? new ArrayList<>(): Arrays.asList(new ObjectMapper().readValue(js, Credit[].class));
        js = jn.get("deposits").toString();
        List<Deposit> deposits;
        deposits = js.equals("")? new ArrayList<>(): Arrays.asList(new ObjectMapper().readValue(js, Deposit[].class));
        return new Client(uuid, name, login, password, passportSeries, passportNumber, passportIssueBy, passportIssueDate, phone, creatingDate, employee, credits, deposits);
    }
}
