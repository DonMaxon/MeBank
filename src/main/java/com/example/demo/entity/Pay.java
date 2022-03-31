package com.example.demo.entity;

import com.example.demo.deserializers.PayDeserializer;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@JsonDeserialize(using = PayDeserializer.class)
@Entity
@Table(name = "Pay")
public class Pay {

    @Id
    private UUID id;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "cash", nullable = false)
    private double cash;
    @ManyToOne
    @JoinColumn(name = "credit")
    @JsonIgnore
    private Credit credit;

    public Pay() {
    }

    public Pay(UUID id) {
        this.id = id;
    }

    public Pay(UUID id, Date date, double cash, Credit credit) {
        this.id = id;
        this.date = date;
        this.cash = cash;
        this.credit = credit;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    @JsonGetter
    public UUID getCreditID() {
        return credit.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pay pay = (Pay) o;
        return Double.compare(pay.cash, cash) == 0 &&
                Objects.equals(id, pay.id) &&
                Objects.equals(date, pay.date) &&
                Objects.equals(credit, pay.credit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, cash, credit);
    }

    @Override
    public String toString() {
        return "Pay{" +
                "id=" + id +
                ", date=" + date +
                ", cash=" + cash +
                ", credit=" + credit +
                '}';
    }
}
