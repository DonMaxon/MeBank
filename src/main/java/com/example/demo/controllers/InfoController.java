package com.example.demo.controllers;


import com.example.demo.entity.Credit;
import com.example.demo.entity.Deposit;
import com.example.demo.entity.Info;
import com.example.demo.entity.Pay;
import com.example.demo.serializers.InfoSerializer;
import com.example.demo.services.CreditService;
import com.example.demo.services.DepositService;
import com.example.demo.services.InfoService;
import com.example.demo.services.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/info")
public class InfoController {

    @Autowired
    InfoService infoService;
    @Autowired
    InfoSerializer infoSerializer;
    @Autowired
    CreditService creditService;
    @Autowired
    DepositService depositService;

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
            Info info = infoSerializer.deserialize(infoString);
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

    /*@RequestMapping(value = "/creditscount",
            method = RequestMethod.GET)
    @ResponseBody
    public Map<Info, Integer> getCreditsCount(){
        List<Info> infos = infoService.getCredits();
        ArrayList<Integer> counts = new ArrayList<>();
        for (int i = 0; i < infos.size(); ++i){
            counts.add(0);
        }

    }*/

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateInfo(@PathVariable("id") UUID id, @RequestBody String clientString){
        if (infoService.findById(id)==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else{
            deleteInfo(id);
            postInfo(clientString);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /*@RequestMapping(value = "/countCredits/",
            method = RequestMethod.GET)
    public ResponseEntity updateInfo(){
        return new ResponseEntity(infoService.getStatsCredits(), HttpStatus.ACCEPTED);
    }*/


}
