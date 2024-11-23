package com.example.cashlimit.model;

public class Income {

    private double total_income;
    private String date_income;
    private String descrip_income;
    private int id_CategoryIncome;
    private int id_user;

    public Income() {
    }

    public Income(double total_income, String date_income, String descrip_income, int id_CategoryIncome, int id_user) {
        this.total_income = total_income;
        this.date_income = date_income;
        this.descrip_income = descrip_income;
        this.id_CategoryIncome = id_CategoryIncome;
        this.id_user = id_user;
    }

    public double getTotal_income() {
        return total_income;
    }

    public void setTotal_income(double total_income) {
        this.total_income = total_income;
    }

    public String getDate_income() {
        return date_income;
    }

    public void setDate_income(String date_income) {
        this.date_income = date_income;
    }

    public String getDescrip_income() {
        return descrip_income;
    }

    public void setDescrip_income(String descrip_income) {
        this.descrip_income = descrip_income;
    }

    public int getId_CategoryIncome() {
        return id_CategoryIncome;
    }

    public void setId_CategoryIncome(int id_CategoryIncome) {
        this.id_CategoryIncome = id_CategoryIncome;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
