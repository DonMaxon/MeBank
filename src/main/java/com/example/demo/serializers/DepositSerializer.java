package com.example.demo.serializers;

import com.example.demo.entity.Deposit;
import com.example.demo.services.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class DepositSerializer {

    @Autowired
    ClientService clientService;

    public String serialize(Deposit deposit) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(deposit);
    }

    public Deposit deserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        Deposit deposit = mapper.readValue(json, Deposit.class);
        deposit.setClient(clientService.findById(deposit.getClientID()));
        return deposit;
    }
}
