package com.example.demo.controllers;


import com.example.demo.entity.Admin;
import com.example.demo.entity.Client;
import com.example.demo.services.AdminService;
import com.example.demo.services.ClientService;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteAdmin(@PathVariable("id") UUID id){
        adminService.delete(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.GET)
    public Admin getAdmin(@PathVariable("id") UUID id){
        return adminService.findById(id);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.POST)
    public ResponseEntity postAdmin(@RequestBody String adminString){
        try {
            Admin admin = Admin.deserialize(adminString);
            adminService.save(admin);
            return new ResponseEntity(admin, HttpStatus.ACCEPTED);
        }
        catch (IOException  e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
