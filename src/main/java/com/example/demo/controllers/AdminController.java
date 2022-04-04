package com.example.demo.controllers;


import com.example.demo.entity.Admin;
import com.example.demo.serializers.AdminSerializer;
import com.example.demo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    AdminSerializer adminSerializer;

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteAdmin(@PathVariable("id") UUID id){
        adminService.delete(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public Admin getAdmin(@PathVariable("id") UUID id){
        return adminService.findById(id);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.POST)
    public ResponseEntity postAdmin(@RequestBody String adminString){
        try {

            Admin admin = adminSerializer.deserialize(adminString);
            adminService.save(admin);
            return new ResponseEntity(admin, HttpStatus.ACCEPTED);
        }
        catch (IOException  e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
