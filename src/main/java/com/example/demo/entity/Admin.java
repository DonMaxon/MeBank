package com.example.demo.entity;

import com.example.demo.AllRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "Admin")
public class Admin {

    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "login", nullable = false)
    private  String  login;
    @Column(name = "password", nullable = false)
    private String password;
    @OneToMany(mappedBy = "admin")
    private List<Employee> employees;
    @OneToMany(mappedBy = "admin")
    private List<Info> infos;

    public Admin(UUID id, String name, String login, String password, List<Employee> employees, List<Info> infos) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.employees = employees;
        this.infos = infos;
    }

    public Admin() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Info> getInfos() {
        return infos;
    }

    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }

    public String serialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(this);
        System.out.println(json);
        return json;
    }

    public Admin deserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        JsonNode jn = mapper.readTree(json);
        UUID uuid = UUID.fromString(jn.get("id").asText());
        String name = jn.get("name").asText();
        String login = jn.get("login").asText();
        String password = jn.get("password").asText();
        String js = jn.get("employees").asText();
        List<Employee> employees;
        employees = js.equals("") ? new ArrayList<>(): new ArrayList<>(Arrays.asList(mapper.readValue(js, Employee[].class)));
        js = jn.get("infos").asText();
        List<Info> infos;
        infos = js.equals("") ? new ArrayList<>(): new ArrayList<>(Arrays.asList(mapper.readValue(js, Info[].class)));
        return new Admin(uuid, name, login, password, employees, infos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return id.equals(admin.id) && name.equals(admin.name) && login.equals(admin.login) && password.equals(admin.password) && employees.equals(admin.employees) && infos.equals(admin.infos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, employees, infos);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", employees=" + employees +
                '}';
    }
}
