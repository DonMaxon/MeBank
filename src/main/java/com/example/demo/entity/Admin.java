package com.example.demo.entity;

import com.example.demo.deserializers.AdminDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@JsonDeserialize(using = AdminDeserializer.class)
@Entity
@Table(name = "Admin")
public class Admin implements UserDetails, CredentialsContainer {

    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "login", nullable = false)
    private  String  login;
    @Column(name = "password", nullable = false)
    private String password;
    @OneToMany(mappedBy = "admin",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees;
    @OneToMany(mappedBy = "admin",  cascade = CascadeType.ALL)
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

    public Admin(UUID adminID) {
        id = adminID;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    @Override
    public void eraseCredentials() {

    }
}
