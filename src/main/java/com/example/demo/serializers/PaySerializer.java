package com.example.demo.serializers;

import com.example.demo.entity.Pay;
import com.example.demo.services.CreditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class PaySerializer {

    @Autowired
    CreditService creditService;

    public String serialize(Pay pay) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(pay);
    }

    public Pay deserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        Pay pay = mapper.readValue(json, Pay.class);
        pay.setCredit(creditService.findById(pay.getCredit().getId()));
        return pay;
    }
}
