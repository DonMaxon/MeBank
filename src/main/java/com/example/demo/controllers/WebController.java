package com.example.demo.controllers;

import com.example.demo.entity.Client;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class WebController {

    @Autowired
    ClientService clientService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/main_client")
    public String clientMain(@RequestParam("client") UUID uuid, Model model) {
        Client client = clientService.findById(uuid);
        model.addAttribute("client", client);
        return "main_client";
    }
    @GetMapping("/deposits")
    public String deposits(@RequestParam("client") UUID uuid, Model model) {
        Client client = clientService.findById(uuid);
        model.addAttribute("client", client);
        return "deposits_client";
    }
    @GetMapping("/credits")
    public String credits(@RequestParam("client") UUID uuid, Model model) {
        Client client = clientService.findById(uuid);
        model.addAttribute("client", client);
        return "credits_client";
    }
}
