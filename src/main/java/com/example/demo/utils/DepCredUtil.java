package com.example.demo.utils;

import java.util.UUID;

public class DepCredUtil {
    private UUID infoID;
    private double summ;
    private int months;

    public DepCredUtil() {
    }

    public DepCredUtil(UUID infoID, double summ, int months) {
        this.infoID = infoID;
        this.summ = summ;
        this.months = months;
    }

    public UUID getInfoID() {
        return infoID;
    }

    public void setInfoID(UUID infoID) {
        this.infoID = infoID;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }
}
