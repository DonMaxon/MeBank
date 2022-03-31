package com.example.demo.serializers;

import com.example.demo.entity.Credit;
import com.example.demo.services.ClientService;
import com.example.demo.services.InfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class CreditSerializer {

    @Autowired
    ClientService clientService;
    @Autowired
    InfoService infoService;

    /*private static CreditSerializer instance = null;

    private CreditSerializer() {

    }

    public static CreditSerializer getInstance() {
        if (instance == null) {
            instance = new CreditSerializer();
        }
        return instance;
    }*/

    public String serialize(Credit credit) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(credit);
    }

    public Credit deserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        Credit credit = mapper.readValue(json, Credit.class);
        credit.setClient(clientService.findById(credit.getClientID()));
        credit.setInfo(infoService.findById(credit.getInfoID()));
        return credit;
    }
}
