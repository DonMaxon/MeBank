package com.example.demo.entity;

import com.example.demo.AllRepository;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    @JsonIgnore
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

    @JsonGetter
    public UUID getCreditID() {
        return credit.getId();
    }

    public String serialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        String json = mapper.writeValueAsString(this);
        System.out.println(json);
        return json;
    }

    public static Pay deserialize(String json) throws JsonProcessingException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        JsonNode jn = mapper.readTree(json);
        UUID uuid = UUID.fromString(jn.get("id").asText());
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(jn.get("date").asText());
        double cash = Double.parseDouble(jn.get("cash").asText());
        Credit credit = AllRepository.findCreditByID(UUID.fromString(jn.get("adminID").asText()));
        return new Pay(uuid, date, cash, credit);
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
