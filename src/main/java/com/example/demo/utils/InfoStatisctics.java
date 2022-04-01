package com.example.demo.utils;

import com.example.demo.entity.Info;

public class InfoStatisctics {

    private Info info;
    private double num;

    public InfoStatisctics(Info answer, double cnt) {
        this.info = answer;
        this.num  = cnt;
    }

    public Info getInfo() {
        return info;
    }

    public double getNum() {
        return num;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public void setNum(double num) {
        this.num = num;
    }
}
