package com.example.demo.entity;

import com.example.demo.deserializers.CreditDeserializer;
import com.example.demo.utils.DepCredUtil;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.util.*;

@JsonDeserialize(using = CreditDeserializer.class)
@Entity
@Table(name = "Credit")
public class Credit {

    @Id
    private UUID id;

    @Column(name = "start_date", nullable = false)
    private Date openDate;
    @Column(name = "end_date", nullable = false)
    private Date endDate;
    @Column(name = "last_pay_date")
    private Date lastPayDate;
    @Column(name = "summ_of_next_pay", nullable = false)
    private double summOfNextPay;
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
    @Column(name = "summ", nullable = false)
    private double summ;
    @OneToMany(mappedBy = "credit",  cascade = CascadeType.ALL)
    private List<Pay> pays;

    public Credit() {
    }

    public Credit(UUID id) {
        this.id = id;
    }

    public Credit(UUID id, double summ, Date openDate, Date endDate, Date lastPayDate,
                  double summOfNextPay, boolean isActive, Client client, Info info, List<Pay> pays) {
        this.id = id;
        this.summ = summ;
        this.openDate = openDate;
        this.endDate = endDate;
        this.lastPayDate = lastPayDate;
        this.summOfNextPay = summOfNextPay;
        this.isActive = isActive;
        this.client = client;
        this.info = info;
        this.pays = pays;
    }

    public Credit(DepCredUtil util, Client clt, Info inf) {
        id = UUID.randomUUID();
        Calendar cal = Calendar.getInstance();
        summ = util.getSumm();
        openDate = cal.getTime();
        cal.add(Calendar.MONTH, util.getMonths());
        endDate = cal.getTime();
        lastPayDate = (Date)openDate.clone();
        info = inf;
        double r = info.getRate()/12/100;
        summOfNextPay = Math.round(util.getSumm()*r*Math.pow(1+r, util.getMonths())/(Math.pow(1+r, util.getMonths())-1)*100)/100.;
        isActive = true;
        client = clt;
        pays = new ArrayList<>();
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

    public Date getLastPayDate() {
        return lastPayDate;
    }

    public void setLastPayDate(Date lastPayDate) {
        this.lastPayDate = lastPayDate;
    }

    public double getSummOfNextPay() {
        return summOfNextPay;
    }

    public void setSummOfNextPay(double summOfNextPay) {
        this.summOfNextPay = summOfNextPay;
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

    public List<Pay> getPays() {
        return pays;
    }

    public void setPays(List<Pay> pays) {
        this.pays = pays;
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
        Credit credit = (Credit) o;
        return Double.compare(credit.summ, summ) == 0 &&
                Double.compare(credit.summOfNextPay, summOfNextPay) == 0 &&
                isActive == credit.isActive &&
                Objects.equals(id, credit.id) &&
                Objects.equals(openDate, credit.openDate) &&
                Objects.equals(endDate, credit.endDate) &&
                Objects.equals(lastPayDate, credit.lastPayDate) &&
                Objects.equals(client, credit.client) &&
                Objects.equals(info, credit.info) &&
                Objects.equals(pays, credit.pays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, summ, openDate, endDate, lastPayDate, summOfNextPay, isActive, client, info, pays);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", summ=" + summ +
                ", openDate=" + openDate +
                ", endDate=" + endDate +
                ", lastPayDate=" + lastPayDate +
                ", summOfNextPay=" + summOfNextPay +
                ", isActive=" + isActive +
                ", client=" + client +
                ", info=" + info +
                ", pays=" + pays +
                '}';
    }
}
