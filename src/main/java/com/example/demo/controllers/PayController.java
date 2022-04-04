package com.example.demo.controllers;

import com.example.demo.entity.Pay;
import com.example.demo.serializers.PaySerializer;
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
    @Autowired
    PaySerializer paySerializer;

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
            Pay pay = paySerializer.deserialize(payString);
            payService.save(pay);
            return new ResponseEntity(pay, HttpStatus.ACCEPTED);
        }
        catch (IOException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
