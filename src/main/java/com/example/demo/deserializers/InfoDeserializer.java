package com.example.demo.deserializers;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Info;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.UUID;


public class InfoDeserializer extends StdDeserializer<Info> {

    public InfoDeserializer() {
        this(null);
    }

    protected InfoDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Info deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jn = mapper.readTree(jsonParser.readValueAsTree().toString());
        UUID uuid = UUID.fromString(jn.get("id").asText());
        Info.Type type = Info.Type.valueOf(jn.get("type").asText());
        String name = jn.get("name").asText();
        double min_summ = Double.parseDouble(jn.get("minSumm").asText());
        double max_summ = Double.parseDouble(jn.get("maxSumm").asText());
        double rate = Double.parseDouble(jn.get("rate").asText());
        Info.Currency currency = Info.Currency.valueOf(jn.get("currency").asText());
        Admin admin = new Admin(UUID.fromString(jn.get("adminID").asText()));
        //Admin admin = AllRepository.findAdminByID(UUID.fromString(jn.get("adminID").asText()));
        //Admin admin = adminService.findById(UUID.fromString(jn.get("adminID").asText()));
        return new Info(uuid, type, name, min_summ, max_summ, rate, currency, admin);
    }
}
