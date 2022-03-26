package com.example.demo.controllers;


import com.example.demo.entity.Deposit;
import com.example.demo.entity.Employee;
import com.example.demo.services.DepositService;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

@Controller
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    DepositService depositService;

    @RequestMapping(value = "/",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteDeposit(@PathVariable("id") UUID id){
        depositService.delete(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.GET)
    public Deposit getDeposit(@PathVariable("id") UUID id){
        return depositService.findById(id);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.POST)
    public ResponseEntity postDeposit(@RequestBody String depositString){
        try {
            Deposit deposit = Deposit.deserialize(depositString);
            depositService.save(deposit);
            return new ResponseEntity(deposit, HttpStatus.ACCEPTED);
        }
        catch (IOException| ParseException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
