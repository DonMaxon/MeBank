package com.example.demo;

import com.example.demo.entity.*;

import java.util.*;

public class AllRepository {
    private static List<Admin> admins = new ArrayList<>();
    private static List<Client> clients = new ArrayList<>();
    private static List<Credit> credits = new ArrayList<>();
    private static List<Deposit> deposits = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();
    private static List<Info> infos = new ArrayList<>();
    private static List<Pay> pays = new ArrayList<>();

    public static void addAdmin(Admin admin) {
        admins.add(admin);
    }
    public static void addClient(Client client) {
        clients.add(client);
    }
    public static void addCredit(Credit credit) {
        credits.add(credit);
    }
    public static void addDeposit(Deposit deposit) {
        deposits.add(deposit);
    }
    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }
    public static void addInfo(Info info) {
        infos.add(info);
    }
    public static void addPay(Pay pay) {
        pays.add(pay);
    }
    public static Admin findAdminByID(UUID id) {
        for (Admin a: admins) {
            if (a.getId().equals(id))
                return a;
        }
        return null;
    }
    public static Client findClientByID(UUID id) {
        for (Client a: clients) {
            if (a.getId().equals(id))
                return a;
        }
        return null;
    }
    public static Credit findCreditByID(UUID id) {
        for (Credit a: credits) {
            if (a.getId().equals(id))
                return a;
        }
        return null;
    }
    public static Deposit findDepositByID(UUID id) {
        for (Deposit a: deposits) {
            if (a.getId().equals(id))
                return a;
        }
        return null;
    }
    public static Employee findEmployeeByID(UUID id) {
        for (Employee a: employees) {
            if (a.getId().equals(id))
                return a;
        }
        return null;
    }
    public static Info findInfoByID(UUID id) {
        for (Info a: infos) {
            if (a.getId().equals(id))
                return a;
        }
        return null;
    }
    public static Pay findPayByID(UUID id) {
        for (Pay a: pays) {
            if (a.getId().equals(id))
                return a;
        }
        return null;
    }
}
