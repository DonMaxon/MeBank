package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

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
    private Credit credit;

    public Pay() {
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
