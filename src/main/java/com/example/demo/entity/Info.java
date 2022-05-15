package com.example.demo.entity;

import com.example.demo.deserializers.InfoDeserializer;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@JsonDeserialize(using = InfoDeserializer.class)
@Entity
@Table(name = "Info")
public class Info {

    public enum Type  {DEPOSIT, CREDIT}
    public enum Currency {RUB, EUR, USD, CNY}

    @Id
    private UUID id;
    @Column(name = "type", nullable = false)
    private Type type;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "min_summ")
    private double minSumm;
    @Column(name = "max_summ")
    private double maxSumm;
    @Column(name = "rate", nullable = false)
    private double rate;
    @Column(name = "currency", nullable = false)
    private Currency currency;
    @ManyToOne
    @JoinColumn(name = "admin")
    @JsonIgnore
    private Admin admin;


    public Info() {
    }

    public Info(UUID id) {
        this.id = id;
    }

    public Info(UUID id, Type type, String name, double minSumm, double maxSumm,
                double rate, Currency currency, Admin admin) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.minSumm = minSumm;
        this.maxSumm = maxSumm;
        this.rate = rate;
        this.currency = currency;
        this.admin = admin;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMinSumm() {
        return minSumm;
    }

    public void setMinSumm(double minSumm) {
        this.minSumm = minSumm;
    }

    public double getMaxSumm() {
        return maxSumm;
    }

    public void setMaxSumm(double maxSumm) {
        this.maxSumm = maxSumm;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @JsonGetter
    private UUID getAdminID() {
        return admin.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return Double.compare(info.minSumm, minSumm) == 0 &&
                Double.compare(info.maxSumm, maxSumm) == 0 &&
                Double.compare(info.rate, rate) == 0 &&
                Objects.equals(id, info.id) &&
                type == info.type &&
                Objects.equals(name, info.name) &&
                currency == info.currency &&
                Objects.equals(admin, info.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, minSumm, maxSumm, rate, currency, admin);
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", minSumm=" + minSumm +
                ", maxSumm=" + maxSumm +
                ", rate=" + rate +
                ", currency=" + currency +
                ", employee=" + admin +
                '}';
    }
}
