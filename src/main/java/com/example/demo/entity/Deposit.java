package com.example.demo.entity;

import com.example.demo.deserializers.DepositDeserializer;
import com.example.demo.utils.DepositUtil;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@JsonDeserialize(using = DepositDeserializer.class)
@Entity
@Table(name = "Deposit")
public class Deposit {

    @Id
    private UUID id;
    @Column(name = "summ", nullable = false)
    private double summ;
    @Column(name = "start_date", nullable = false)
    private Date openDate;
    @Column(name = "end_date", nullable = false)
    private Date endDate;
    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    @ManyToOne
    @JoinColumn(name = "client")
    @JsonIgnore
    private Client client;
    @ManyToOne
    @JoinColumn(name = "info")
    @JsonIgnore
    private Info info;

    public Deposit() {
    }

    public Deposit(UUID id) {
        this.id = id;
    }

    public Deposit(UUID id, double summ, Date openDate, Date endDate, boolean isActive, Client client, Info info) {
        this.id = id;
        this.summ = summ;
        this.openDate = openDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.client = client;
        this.info = info;
    }

    public Deposit(DepositUtil util, Client clt, Info inf) {
        id = UUID.randomUUID();
        summ = util.getSumm();
        Calendar cal = Calendar.getInstance();
        openDate = cal.getTime();
        cal.add(Calendar.MONTH, util.getMonths());
        endDate = cal.getTime();
        isActive = true;
        client = clt;
        info = inf;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @JsonGetter
    public UUID getClientID() {
        return client.getId();
    }

    @JsonGetter
    public UUID getInfoID() {
        return info.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit deposit = (Deposit) o;
        return Double.compare(deposit.summ, summ) == 0 &&
                isActive == deposit.isActive &&
                Objects.equals(id, deposit.id) &&
                Objects.equals(openDate, deposit.openDate) &&
                Objects.equals(endDate, deposit.endDate) &&
                Objects.equals(client, deposit.client) &&
                Objects.equals(info, deposit.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, summ, openDate, endDate, isActive, client, info);
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", summ=" + summ +
                ", openDate=" + openDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                ", client=" + client +
                ", info=" + info +
                '}';
    }
}
