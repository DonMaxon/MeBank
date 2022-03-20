package com.example.demo.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Credit")
public class Credit {

    @Id
    private UUID id;
    @Column(name = "summ", nullable = false)
    private double summ;
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
    private Client client;
    @ManyToOne
    @JoinColumn(name = "info")
    private Info info;
    @OneToMany(mappedBy = "credit")
    private List<Pay> pays;

    public Credit() {
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
