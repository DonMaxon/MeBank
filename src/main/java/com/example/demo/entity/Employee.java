package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "login", nullable = false)
    private  String  login;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "admin")
    private Admin admin;
    @OneToMany(mappedBy = "employee")
    private List<Client> clients;

    public Employee() {
    }

    public Employee(UUID id, String name, String login, String password, Admin admin, List<Client> clients) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.admin = admin;
        this.clients = clients;
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(login, employee.login) &&
                Objects.equals(password, employee.password) &&
                Objects.equals(admin, employee.admin) &&
                Objects.equals(clients, employee.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, admin, clients);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", clients=" + clients +
                '}';
    }
}
