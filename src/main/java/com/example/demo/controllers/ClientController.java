package com.example.demo.controllers;


import com.example.demo.entity.Client;
import com.example.demo.entity.Credit;
import com.example.demo.entity.Employee;
import com.example.demo.services.ClientService;
import com.example.demo.services.CreditService;
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
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

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
            Client client = Client.deserialize(clientString);
            clientService.save(client);
            return new ResponseEntity(client, HttpStatus.ACCEPTED);
        }
        catch (IOException | ParseException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/all",
            method = RequestMethod.GET)
    public ResponseEntity getAllEmployees(){
        List<Client> res = clientService.findAll();
        return new ResponseEntity(res, HttpStatus.ACCEPTED);
    }


}
