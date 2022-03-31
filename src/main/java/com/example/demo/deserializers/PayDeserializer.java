package com.example.demo.deserializers;

import com.example.demo.AllRepository;
import com.example.demo.entity.Credit;
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
import java.util.Date;
import java.util.UUID;

public class PayDeserializer extends StdDeserializer<Pay> {

    public PayDeserializer() {
        this(null);
    }

    protected PayDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Pay deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        JsonNode jn = mapper.readTree(jsonParser.readValueAsTree().toString());
        UUID uuid = UUID.fromString(jn.get("id").asText());
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(jn.get("date").asText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double cash = Double.parseDouble(jn.get("cash").asText());
        //Credit credit = AllRepository.findCreditByID(UUID.fromString(jn.get("creditID").asText()));
        Credit credit = new Credit(UUID.fromString(jn.get("creditID").asText()));
        return new Pay(uuid, date, cash, credit);
    }
}
