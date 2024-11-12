package com.example.cashlimit.model;

import java.util.Date;

public class Bills {
    private double total_bill;
    private String date_bill;
    private String descrip_bill;
    private int id_CategoryBill;
    private int id_user;

    public Bills() {
    }

    public Bills(double total_bill, String date_bill, String descrip_bill, int id_CategoryBill, int id_user) {
        this.total_bill = total_bill;
        this.date_bill = date_bill;
        this.descrip_bill = descrip_bill;
        this.id_CategoryBill = id_CategoryBill;
        this.id_user = id_user;
    }

    public double getTotal_bill() {
        return total_bill;
    }

    public void setTotal_bill(double total_bill) {
        this.total_bill = total_bill;
    }


    public String getDate_bill() {
        return date_bill;
    }

    public void setDate_bill(String date_bill) {
        this.date_bill = date_bill;
    }

    public String getDescrip_bill() {
        return descrip_bill;
    }

    public void setDescrip_bill(String descrip_bill) {
        this.descrip_bill = descrip_bill;
    }

    public int getId_CategoryBill() {
        return id_CategoryBill;
    }

    public void setId_CategoryBill(int id_CategoryBill) {
        this.id_CategoryBill = id_CategoryBill;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
