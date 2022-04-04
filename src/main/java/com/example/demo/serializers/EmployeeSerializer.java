package com.example.demo.serializers;

import com.example.demo.entity.Employee;
import com.example.demo.services.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class EmployeeSerializer {

    @Autowired
    AdminService adminService;

    public String serialize(Employee employee) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(employee);
    }

    public Employee deserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        Employee employee = mapper.readValue(json, Employee.class);
        employee.setAdmin(adminService.findById(employee.getAdminID()));
        return employee;
    }
}
