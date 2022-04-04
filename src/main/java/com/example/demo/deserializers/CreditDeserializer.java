package com.example.demo.deserializers;

import com.example.demo.entity.Client;
import com.example.demo.entity.Credit;
import com.example.demo.entity.Info;
import com.example.demo.entity.Pay;
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

public class CreditDeserializer extends StdDeserializer<Credit> {

    public CreditDeserializer() {
        this(null);
    }

    protected CreditDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Credit deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
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
        Date lastPayDate = null;
        try {
            lastPayDate = new SimpleDateFormat("dd-MM-yyyy").parse(jn.get("lastPayDate").asText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double summOfNextPay = Double.parseDouble(jn.get("summOfNextPay").asText());
        boolean isActive = Boolean.parseBoolean(jn.get("active").asText());
        //Client client = AllRepository.findClientByID(UUID.fromString(jn.get("clientID").asText()));
        //Info info = AllRepository.findInfoByID(UUID.fromString(jn.get("infoID").asText()));
        Client client = new Client(UUID.fromString(jn.get("clientID").asText()));
        Info info = new Info(UUID.fromString(jn.get("infoID").asText()));
        String js = jn.get("pays").toString();
        List<Pay> pays;
        pays = js.equals("") ? new ArrayList<>(): Arrays.asList(new ObjectMapper().readValue(js, Pay[].class));
        return new Credit(uuid, summ, openDate, endDate, lastPayDate, summOfNextPay, isActive, client, info, pays);
    }
}
