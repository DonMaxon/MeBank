package com.example.demo.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Info")
public class Info {

    public enum Type  {DEPOSIT, CREDIT};
    public enum Currency {RUB, EUR, USD}

    @Id
    private UUID id;
    @Column(name = "type", nullable = false)
    private Type type;
    @Column(name = "name", nullable = false)
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
    private Admin admin;
    @OneToMany(mappedBy = "info")
    private List<Credit> credits;
    @OneToMany(mappedBy = "info")
    private List<Deposit> deposits;

    public Info() {
    }

    public Info(UUID id, Type type, String name, double minSumm, double maxSumm,
                double rate, Currency currency, Admin admin, List<Credit> credits, List<Deposit> deposits) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.minSumm = minSumm;
        this.maxSumm = maxSumm;
        this.rate = rate;
        this.currency = currency;
        this.admin = admin;
        this.credits = credits;
        this.deposits = deposits;
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

    public Admin getEmployee() {
        return admin;
    }

    public void setEmployee(Admin admin) {
        this.admin = admin;
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
        Info info = (Info) o;
        return Double.compare(info.minSumm, minSumm) == 0 &&
                Double.compare(info.maxSumm, maxSumm) == 0 &&
                Double.compare(info.rate, rate) == 0 &&
                Objects.equals(id, info.id) &&
                type == info.type &&
                Objects.equals(name, info.name) &&
                currency == info.currency &&
                Objects.equals(admin, info.admin) &&
                Objects.equals(credits, info.credits) &&
                Objects.equals(deposits, info.deposits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, minSumm, maxSumm, rate, currency, admin, credits, deposits);
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
                ", credits=" + credits +
                ", deposits=" + deposits +
                '}';
    }
}
