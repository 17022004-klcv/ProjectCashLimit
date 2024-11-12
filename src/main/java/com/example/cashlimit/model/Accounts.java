package com.example.cashlimit.model;

public class Accounts {

    private String num_account;
    private Double amount;
    private String bank;
    private int id_user;
    private int id_type_account;
    private String status_account;

    public Accounts() {
    }

    public Accounts(String num_account, Double amount, String bank, int id_user, int id_type_account, String status_account) {
        this.num_account = num_account;
        this.amount = amount;
        this.bank = bank;
        this.id_user = id_user;
        this.id_type_account = id_type_account;
        this.status_account = status_account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getId_type_account() {
        return id_type_account;
    }

    public void setId_type_account(int id_type_account) {
        this.id_type_account = id_type_account;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNum_account() {
        return num_account;
    }

    public void setNum_account(String num_account) {
        this.num_account = num_account;
    }

    public String getStatus_account() {
        return status_account;
    }

    public void setStatus_account(String status_account) {
        this.status_account = status_account;
    }
}
