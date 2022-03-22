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
import java.util.Date;
import java.util.Objects;
import java.util.UUID;


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

    public Deposit(UUID id, double summ, Date openDate, Date endDate, boolean isActive, Client client, Info info) {
        this.id = id;
        this.summ = summ;
        this.openDate = openDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.client = client;
        this.info = info;
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

    public String serialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(this);
        System.out.println(json);
        return json;
    }

    public Deposit deserialize(String json) throws JsonProcessingException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        JsonNode jn = mapper.readTree(json);
        UUID uuid = UUID.fromString(jn.get("id").asText());
        double summ = Double.parseDouble(jn.get("summ").asText());
        Date openDate = new SimpleDateFormat("dd-MM-yyyy").parse(jn.get("openDate").asText());
        Date endDate = new SimpleDateFormat("dd-MM-yyyy").parse(jn.get("endDate").asText());
        boolean isActive = Boolean.parseBoolean(jn.get("active").asText());
        Client client = AllRepository.findClientByID(UUID.fromString(jn.get("clientID").asText()));
        Info info = AllRepository.findInfoByID(UUID.fromString(jn.get("infoID").asText()));
        return new Deposit(uuid, summ, openDate, endDate, isActive, client, info);
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
