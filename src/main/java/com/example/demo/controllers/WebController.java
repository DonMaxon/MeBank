package com.example.demo.controllers;

import com.example.demo.entity.Client;
import com.example.demo.entity.Deposit;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Info;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.services.ClientService;
import com.example.demo.services.DepositService;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.InfoService;
import com.example.demo.utils.DepositUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class WebController {

    @Autowired
    ClientService clientService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    InfoService infoService;
    @Autowired
    DepositService depositService;

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
    @GetMapping("/main_employee")
    public String employeeMain(@RequestParam("employee") UUID uuid, Model model) {
        Employee employee = employeeService.findById(uuid);
        model.addAttribute("employee", employee);
        return "main_employee";
    }
    @GetMapping("/clients")
    public String clientsEmp(@RequestParam("employee") UUID uuid, Model model) {
        Employee employee = employeeService.findById(uuid);
        model.addAttribute("employee", employee);
        return "clients_employee";
    }
    @GetMapping("/new_dep")
    public String newDeposit(@RequestParam("client") UUID uuid, Model model) {
        Client client = clientService.findById(uuid);
        model.addAttribute("client", client);
        List<Info> deposits = infoService.getDeposits();
        model.addAttribute("infos", deposits);
        model.addAttribute("deposit", new DepositUtil());
        return "new_deposit";
    }
    @PostMapping("/new_dep")
    public String createNewDeposit(@RequestParam("client") UUID uuid, @ModelAttribute DepositUtil depositUtil, Model model) {
        Client client = clientService.findById(uuid);
        Info info = infoService.findById(depositUtil.getInfoID());
        Deposit deposit = new Deposit(depositUtil, client, info);
        depositService.save(deposit);
        return "redirect:/deposits?client="+uuid.toString();
    }
}
