package com.example.demo.controllers;


import com.example.demo.entity.Credit;
import com.example.demo.entity.Deposit;
import com.example.demo.services.CreditService;
import com.example.demo.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

@Controller
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    CreditService creditService;

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteCredit(@PathVariable("id") UUID id){
        creditService.delete(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public Credit getCredit(@PathVariable("id") UUID id){
        return creditService.findById(id);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.POST)
    public ResponseEntity postClient(@RequestBody String creditString){
        try {
            Credit credit = Credit.deserialize(creditString);
            creditService.save(credit);
            return new ResponseEntity(credit, HttpStatus.ACCEPTED);
        }
        catch (IOException | ParseException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
