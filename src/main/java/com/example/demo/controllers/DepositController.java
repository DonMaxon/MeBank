package com.example.demo.controllers;


import com.example.demo.entity.Credit;
import com.example.demo.entity.Deposit;
import com.example.demo.entity.Employee;
import com.example.demo.serializers.DepositSerializer;
import com.example.demo.services.DepositService;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    DepositService depositService;

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteDeposit(@PathVariable("id") UUID id){
        depositService.delete(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public Deposit getDeposit(@PathVariable("id") UUID id){
        return depositService.findById(id);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.POST)
    public ResponseEntity postDeposit(@RequestBody String depositString){
        try {
            Deposit deposit = DepositSerializer.deserialize(depositString);
            depositService.save(deposit);
            return new ResponseEntity(deposit, HttpStatus.ACCEPTED);
        }
        catch (IOException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/all",
            method = RequestMethod.GET)
    public ResponseEntity getAllDeposits(){
        List<Deposit> res = depositService.findAll();
        return new ResponseEntity(res, HttpStatus.ACCEPTED);
    }
}
