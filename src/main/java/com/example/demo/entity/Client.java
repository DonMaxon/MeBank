package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Client")
public class Client {

    @Id
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "login", nullable = false)
    private  String  login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column (name = "passport_series", nullable = false)
    private String passportSeries;
    @Column (name = "passport_number", nullable = false)
    private String passportNumber;
    @Column (name = "passport_issue_by", nullable = false)
    private String passportIssueBy;
    @Column (name = "passport_issue_date", nullable = false)
    private Date passportIssueDate;
    @Column (name = "phone", nullable = false)
    private String phone;
    @Column (name = "creating_date", nullable = false)
    private Date creatingDate;
    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;
    @OneToMany(mappedBy = "client")
    private List<Credit> credits;
    @OneToMany(mappedBy = "client")
    private List<Deposit> deposits;

    public Client() {
    }

    public Client(UUID id, String name, String login, String password,
                  String passportSeries, String passportNumber, String passportIssueBy,
                  Date passportIssueDate, String phone, Date creatingDate, Employee employee,
                  List<Credit> credits, List<Deposit> deposits) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.passportIssueBy = passportIssueBy;
        this.passportIssueDate = passportIssueDate;
        this.phone = phone;
        this.creatingDate = creatingDate;
        this.employee = employee;
        this.credits = credits;
        this.deposits = deposits;
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

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportIssueBy() {
        return passportIssueBy;
    }

    public void setPassportIssueBy(String passportIssueBy) {
        this.passportIssueBy = passportIssueBy;
    }

    public Date getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(Date passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Date creatingDate) {
        this.creatingDate = creatingDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(name, client.name) &&
                Objects.equals(login, client.login) &&
                Objects.equals(password, client.password) &&
                Objects.equals(passportSeries, client.passportSeries) &&
                Objects.equals(passportNumber, client.passportNumber) &&
                Objects.equals(passportIssueBy, client.passportIssueBy) &&
                Objects.equals(passportIssueDate, client.passportIssueDate) &&
                Objects.equals(phone, client.phone) &&
                Objects.equals(creatingDate, client.creatingDate) &&
                Objects.equals(employee, client.employee) &&
                Objects.equals(credits, client.credits) &&
                Objects.equals(deposits, client.deposits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, passportSeries,
                passportNumber, passportIssueBy, passportIssueDate, phone, creatingDate, employee, credits, deposits);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportIssueBy='" + passportIssueBy + '\'' +
                ", passportIssueDate=" + passportIssueDate +
                ", phone='" + phone + '\'' +
                ", creatingDate=" + creatingDate +
                ", employee=" + employee +
                ", credits=" + credits +
                ", deposits=" + deposits +
                '}';
    }
}
