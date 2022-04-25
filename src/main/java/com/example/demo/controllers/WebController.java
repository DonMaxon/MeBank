package com.example.demo.controllers;

import com.example.demo.entity.*;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.services.*;
import com.example.demo.utils.DepCredUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @Autowired
    CreditService creditService;
    @Autowired
    AdminService adminService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/error")
    public String error(Model model) { return "error"; }

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
        model.addAttribute("deposit", new DepCredUtil());
        return "new_deposit";
    }
    @PostMapping("/new_dep")
    public String createNewDeposit(@RequestParam("client") UUID uuid, @ModelAttribute DepCredUtil depositUtil, Model model) {
        Client client = clientService.findById(uuid);
        Info info = infoService.findById(depositUtil.getInfoID());
        Deposit deposit = new Deposit(depositUtil, client, info);
        depositService.save(deposit);
        return "redirect:/deposits?client="+uuid.toString();
    }
    @GetMapping("/new_cred")
    public String newCredit(@RequestParam("client") UUID uuid, Model model) {
        Client client = clientService.findById(uuid);
        model.addAttribute("client", client);
        List<Info> credits = infoService.getCredits();
        model.addAttribute("infos", credits);
        model.addAttribute("credit", new DepCredUtil());
        return "new_credit";
    }
    @PostMapping("/new_cred")
    public String createNewCredit(@RequestParam("client") UUID uuid, @ModelAttribute DepCredUtil creditUtil, Model model) {
        Client client = clientService.findById(uuid);
        Info info = infoService.findById(creditUtil.getInfoID());
        Credit credit = new Credit(creditUtil, client, info);
        creditService.save(credit);
        return "redirect:/credits?client="+uuid.toString();
    }
    @GetMapping("/main_admin")
    public String adminMain(@RequestParam("admin") UUID uuid, Model model) {
        Admin admin = adminService.findById(uuid);
        model.addAttribute("admin", admin);
        return "main_admin";
    }
    @GetMapping("/all_info")
    public String allInfo(@RequestParam("admin") UUID uuid, Model model) {
        Admin admin = adminService.findById(uuid);
        model.addAttribute("admin", admin);
        List<Info> deposits = infoService.getDeposits();
        model.addAttribute("infodep", deposits);
        List<Info> credits = infoService.getCredits();
        model.addAttribute("infocrd", credits);
        return "all_info";
    }
    @GetMapping("/upd_info")
    public String updInfo(@RequestParam("info") UUID uuid, @RequestParam("admin") UUID id, Model model) {
        Info info = infoService.findById(uuid);
        model.addAttribute("info", info);
        Admin admin = adminService.findById(id);
        model.addAttribute("admin", admin);
        return "upd_info";
    }
    @PostMapping("upd_info")
    public String updInfoParams(@RequestParam("info") UUID id, @RequestParam("admin") UUID uuid, Model model, @ModelAttribute Info info) {
        Info toUpd = infoService.findById(id);
        info.setAdmin(adminService.findById(uuid));
        info.setType(Info.Type.DEPOSIT);
        try {
            infoService.delete(id);
        }
        catch(Exception e) {
            return "redirect:/new_info?admin="+uuid.toString();
        }
        infoService.save(info);
        return "redirect:/all_info?admin="+uuid.toString();
    }
    @GetMapping("new_info")
    public String newInfo(@RequestParam("admin") UUID uuid, Model model) {
        model.addAttribute("admin", adminService.findById(uuid));
        model.addAttribute("types", Info.Type.values());
        model.addAttribute("currencies", Info.Currency.values());
        model.addAttribute("info", new Info());
        return "new_info";
    }
    @PostMapping("new_info")
    public String newInfoCreate(@RequestParam("admin") UUID uuid, Model model, @ModelAttribute Info info) {
        info.setAdmin(adminService.findById(uuid));
        info.setId(UUID.randomUUID());
        if (info.getType().equals(Info.Type.DEPOSIT)) {
            List<Info> deps = infoService.getDeposits();
            for (Info dep: deps) {
                if (info.getName().equals(dep.getName()))
                    return "redirect:/new_info?admin="+uuid.toString();
            }
        } else {
            List<Info> creds = infoService.getCredits();
            for (Info crd: creds) {
                if (info.getName().equals(crd.getName()))
                    return "redirect:/new_info?admin=" + uuid.toString();
            }
        }
        infoService.save(info);
        return "redirect:/all_info?admin="+uuid.toString();
    }
    @GetMapping("all_clients")
    public String allClients(@RequestParam("admin") UUID uuid, Model model) {
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("admin", adminService.findById(uuid));
        return "all_clients";
    }
    @GetMapping("new_client")
    public String newClient(@RequestParam("creator") UUID uuid, Model model) {
        model.addAttribute("creator", employeeService.findById(uuid));
        model.addAttribute("client", new Client());
        return "new_client";
    }
    @PostMapping("new_client")
    public String newClientCreate(@RequestParam("creator") UUID uuid, Model model, @ModelAttribute Client client) {
        client.setId(UUID.randomUUID());
        client.setEmployee(employeeService.findById(uuid));
        client.setCreatingDate(new Date());
        client.setCredits(new ArrayList<>());
        client.setDeposits(new ArrayList<>());
        clientService.save(client);
        return "redirect:/clients?employee="+uuid.toString();
    }
    @GetMapping("upd_client")
    public String updClient(@RequestParam("client") UUID uuid, @RequestParam("employee") UUID id, Model model) {
        model.addAttribute("client", clientService.findById(uuid));
        model.addAttribute("employee", employeeService.findById(id));
        return "upd_client";
    }
    @GetMapping("del_client")
    public String delClient(@RequestParam("client") UUID uuid, @RequestParam("employee") UUID id, Model model) {
        clientService.delete(uuid);
        return "redirect:/clients?employee="+id.toString();
    }
    @GetMapping("delete_info")
    public String delInfo(@RequestParam("info") UUID uuid, @RequestParam("admin") UUID id, Model model) {
        infoService.delete(uuid);
        return "redirect:/all_info?admin="+id.toString();
    }
    @GetMapping("all_employees")
    public String allEmp(@RequestParam("admin") UUID uuid, Model model) {
        model.addAttribute("admin", adminService.findById(uuid));
        model.addAttribute("employees", employeeService.findAll());
        return "all_employees";
    }
    @GetMapping("upd_employee")
    public String updEmp(@RequestParam("employee") UUID uuid, @RequestParam("creator") UUID id, Model model) {
        model.addAttribute("creator", adminService.findById(id));
        model.addAttribute("employee", employeeService.findById(uuid));
        return "upd_employee";
    }
    @PostMapping("upd_employee")
    public String updEmpParams(@RequestParam("creator") UUID id, Model model, @ModelAttribute Employee employee) {
        return "redirect:/all_employees?admin="+id.toString();
    }
    @GetMapping("del_employee")
    public String delEmp(@RequestParam("employee") UUID uuid, @RequestParam("admin") UUID id, Model model) {
        employeeService.delete(uuid);
        return "redirect:/all_employees?admin="+id.toString();
    }
    @GetMapping("new_employee")
    public String newEmp(@RequestParam("creator") UUID uuid, Model model) {
        model.addAttribute("admin", adminService.findById(uuid));
        model.addAttribute("employee", new Employee());
        return "new_employee";
    }
    @PostMapping("new_employee")
    public String newEmpCreate(@RequestParam("creator") UUID uuid, Model model, @ModelAttribute Employee employee) {
        employee.setId(UUID.randomUUID());
        employee.setAdmin(adminService.findById(uuid));
        employee.setClients(new ArrayList<>());
        employeeService.save(employee);
        return "redirect:/all_employees?admin="+uuid.toString();
    }
    @GetMapping("stats")
    public String stats(@RequestParam("admin") UUID uuid, Model model) {
        model.addAttribute("admin", adminService.findById(uuid));
        model.addAttribute("depCount", depositService.countByType());
        model.addAttribute("crdCount", creditService.countByType());
        model.addAttribute("depProfit", depositService.profitByType());
        model.addAttribute("crdProfit", creditService.profitByType());
        return "stats";
    }

}
