package com.example.demo.controllers;


import com.example.demo.entity.Deposit;
import com.example.demo.serializers.DepositSerializer;
import com.example.demo.services.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    DepositService depositService;
    @Autowired
    DepositSerializer depositSerializer;

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
            Deposit deposit = depositSerializer.deserialize(depositString);
            depositService.save(deposit);
            return new ResponseEntity(deposit, HttpStatus.ACCEPTED);
        }
        catch (IOException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateDeposit(@PathVariable("id") UUID id, @RequestBody String clientString){
        if (depositService.findById(id)==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else{
            deleteDeposit(id);
            postDeposit(clientString);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/all",
            method = RequestMethod.GET)
    public ResponseEntity getAllDeposits(){
        List<Deposit> res = depositService.findAll();
        return new ResponseEntity(res, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/count/type", method = RequestMethod.GET)
    public ResponseEntity countByType() {
        List<?> res = depositService.countByType();
        return new ResponseEntity(res, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/profit/type", method = RequestMethod.GET)
    public ResponseEntity profitByType() {
        List<?> res = depositService.profitByType();
        return new ResponseEntity(res, HttpStatus.ACCEPTED);
    }

}
