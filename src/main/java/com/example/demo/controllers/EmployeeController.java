package com.example.demo.controllers;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Info;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteEmployee(@PathVariable("id") UUID id){
        employeeService.delete(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public Employee getEmployee(@PathVariable("id") UUID id){
        return employeeService.findById(id);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.POST)
    public ResponseEntity postEmployee(@RequestBody String employeeString){
        try {
            Employee employee = Employee.deserialize(employeeString);
            employeeService.save(employee);
            return new ResponseEntity(employee, HttpStatus.ACCEPTED);
        }
        catch (IOException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
