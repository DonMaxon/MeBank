package com.example.demo.controllers;


import com.example.demo.entity.*;
import com.example.demo.serializers.ClientSerializer;
import com.example.demo.services.ClientService;
import com.example.demo.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;
    @Autowired
    ClientSerializer clientSerializer;

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteClient(@PathVariable("id") UUID id){
        clientService.delete(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public Client getClient(@PathVariable("id") UUID id){
        return clientService.findById(id);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.POST)
    public ResponseEntity postClient(@RequestBody String clientString){
        try {
            Client client = clientSerializer.deserialize(clientString);
            clientService.save(client);
            return new ResponseEntity(client, HttpStatus.ACCEPTED);
        }
        catch (IOException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/all",
            method = RequestMethod.GET)
    public ResponseEntity getAllEmployees(){
        List<Client> res = clientService.findAll();
        return new ResponseEntity(res, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/deposits/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Deposit> getDepositsOfClient(@PathVariable("id") UUID id){
        return clientService.findById(id).getDeposits();
    }

    @RequestMapping(value = "/credits/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Credit> getCreditsOfClient(@PathVariable("id") UUID id){
        return clientService.findById(id).getCredits();
    }

    @RequestMapping(value = "/credits/pays/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Pay> getPaysOfClient(@PathVariable("id") UUID id){
        List<Credit> credits = clientService.findById(id).getCredits();
        List<Pay> pays = new ArrayList<>();
        for (int i =0; i < credits.size(); ++i){
            pays.addAll(credits.get(i).getPays());
        }
        return pays;
    }



}
