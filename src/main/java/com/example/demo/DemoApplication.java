package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.serializers.AdminSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws JsonProcessingException, ParseException {
		SpringApplication.run(DemoApplication.class, args);
		Admin admin = new Admin(UUID.randomUUID(), "John", "dungeon_master", "1234", new ArrayList<>(), new ArrayList<>());
		Admin admin1 = new Admin(UUID.randomUUID(), "Peter", "boss_of_this_gym", "5678", new ArrayList<>(), new ArrayList<>());
		Info info = new Info(UUID.randomUUID(), Info.Type.CREDIT, "cool", 1, 100, 20, Info.Currency.RUB, admin);
		Info dep = new Info(UUID.randomUUID(), Info.Type.DEPOSIT, "cool", 1, 100, 20, Info.Currency.RUB, admin);
		Info dep1 = new Info(UUID.randomUUID(), Info.Type.DEPOSIT, "cooler", 1, 1000, 21, Info.Currency.RUB, admin1);
		Employee emp1 = new Employee(UUID.randomUUID(), "Will", "best_banker", "1234", admin, new ArrayList<>());
		Employee emp2 = new Employee(UUID.randomUUID(), "Sue", "bts", "chonguk", admin, new ArrayList<>());
		Client cl1 = new Client(UUID.randomUUID(), "JaSON", "json", "json", "1234", "567890", "ГУ МВД", new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2012"), "89370000000", new SimpleDateFormat("dd-MM-yyyy").parse("11-11-2021"), emp1, new ArrayList<>(), new ArrayList<>());
		Client cl2 = new Client(UUID.randomUUID(), "GaSON", "gson", "gson", "1235", "567890", "ГУ МВД", new SimpleDateFormat("dd-MM-yyyy").parse("09-12-1992"), "89171111111", new SimpleDateFormat("dd-MM-yyyy").parse("12-11-2021"), emp1, new ArrayList<>(), new ArrayList<>());
		Deposit depcl1 = new Deposit(UUID.randomUUID(), 100, new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2012"), new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2013"), true, cl1, dep);
		Credit credcl1 = new Credit(UUID.randomUUID(), 100, new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2012"), new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2013"), new SimpleDateFormat("dd-MM-yyyy").parse("12-01-2013"), 10, true, cl1, info, new ArrayList<>());
		Pay pay1 = new Pay(UUID.randomUUID(), new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2012"), 10, credcl1);
		admin.getInfos().add(info);
		admin.getInfos().add(dep);
		admin1.getInfos().add(dep1);
		admin.getEmployees().add(emp1);
		admin.getEmployees().add(emp2);
		emp1.getClients().add(cl1);
		emp1.getClients().add(cl2);
		cl1.getCredits().add(credcl1);
		cl1.getDeposits().add(depcl1);
		credcl1.getPays().add(pay1);

		AllRepository.addAdmin(admin);
		AllRepository.addAdmin(admin1);
		AllRepository.addInfo(info);
		AllRepository.addInfo(dep);
		AllRepository.addInfo(dep1);
		AllRepository.addEmployee(emp1);
		AllRepository.addEmployee(emp2);
		AllRepository.addClient(cl1);
		AllRepository.addClient(cl2);
		AllRepository.addDeposit(depcl1);
		AllRepository.addCredit(credcl1);
		AllRepository.addPay(pay1);

		String j3 = AdminSerializer.getInstance().serialize(admin);
		System.out.println();
		System.out.println(j3);
		Admin a2 = AdminSerializer.deserialize(j3);
		System.out.println();
		System.out.println(AdminSerializer.getInstance().serialize(a2));
		int i;

	}

}
