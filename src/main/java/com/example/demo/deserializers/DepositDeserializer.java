package com.example.demo.deserializers;

import com.example.demo.AllRepository;
import com.example.demo.entity.Client;
import com.example.demo.entity.Deposit;
import com.example.demo.entity.Info;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class DepositDeserializer extends StdDeserializer<Deposit> {

    public DepositDeserializer() {
        this(null);
    }

    protected DepositDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Deposit deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        JsonNode jn = mapper.readTree(jsonParser.readValueAsTree().toString());
        UUID uuid = UUID.fromString(jn.get("id").asText());
        double summ = Double.parseDouble(jn.get("summ").asText());
        Date openDate = null;
        try {
            openDate = new SimpleDateFormat("dd-MM-yyyy").parse(jn.get("openDate").asText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = new SimpleDateFormat("dd-MM-yyyy").parse(jn.get("endDate").asText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean isActive = Boolean.parseBoolean(jn.get("active").asText());
        Client client = AllRepository.findClientByID(UUID.fromString(jn.get("clientID").asText()));
        Info info = AllRepository.findInfoByID(UUID.fromString(jn.get("infoID").asText()));
        return new Deposit(uuid, summ, openDate, endDate, isActive, client, info);
    }
}
