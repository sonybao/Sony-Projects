package com.example.sbfood;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MenuList {
    private String tenmon;
    private int giamon;
    private String mota;
    private int hinhanh;



    public MenuList(String tenmon, int giamon, String mota, int hinhanh) {
        this.tenmon = tenmon;
        this.giamon = giamon;
        this.mota = mota;
        this.hinhanh = hinhanh;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public int getGiamon() {
        return giamon;
    }

    public void setGiamon(int giamon) {
        this.giamon = giamon;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }



}
