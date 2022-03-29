package com.example.demo.controllers;

import com.example.demo.entity.Pay;
import com.example.demo.services.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    PayService payService;

    /*@RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deletePay(@PathVariable("id") UUID id){
        payService.delete(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }*/

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public Pay getPay(@PathVariable("id") UUID id){
        return payService.findById(id);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.POST)
    public ResponseEntity postPay(@RequestBody String payString){
        try {
            Pay pay = Pay.deserialize(payString);
            payService.save(pay);
            return new ResponseEntity(pay, HttpStatus.ACCEPTED);
        }
        catch (IOException| ParseException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
