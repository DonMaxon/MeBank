package com.example.demo.serializers;

import com.example.demo.entity.Info;
import com.example.demo.services.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class InfoSerializer {

    @Autowired
    private AdminService adminService;

    public String serialize(Info info) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(info);
    }

    public Info deserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        Info info = mapper.readValue(json, Info.class);
        info.setAdmin(adminService.findById(info.getAdmin().getId()));
        return info;
    }
}
