package com.example.demo.controllers;


import com.example.demo.entity.Info;
import com.example.demo.entity.Pay;
import com.example.demo.services.InfoService;
import com.example.demo.services.PayService;
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
@RequestMapping("/info")
public class InfoController {

    @Autowired
    InfoService infoService;

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteInfo(@PathVariable("id") UUID id){
        infoService.delete(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public Info getInfo(@PathVariable("id") UUID id){
        return infoService.findById(id);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.POST)
    public ResponseEntity postInfo(@RequestBody String infoString){
        try {
            Info info = Info.deserialize(infoString);
            infoService.save(info);
            return new ResponseEntity(info, HttpStatus.ACCEPTED);
        }
        catch (IOException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/credits",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Info> getCreditsInfo(){
        return infoService.getCredits();
    }

    @RequestMapping(value = "/deposits",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Info> getDepositsInfo(){
        return infoService.getDeposits();
    }

}
