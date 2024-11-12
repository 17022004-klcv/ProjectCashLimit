package com.example.cashlimit.model;

public class User {

    private String name_user;
    private String lastname_user;
    private String mail_user;
    private String tel_user;


    public User() {
    }

    public User(String name_user, String lastname_user, String mail_user, String tel_user) {
        this.lastname_user = lastname_user;
        this.mail_user = mail_user;
        this.name_user = name_user;
        this.tel_user = tel_user;
    }

    public String getLastname_user() {
        return lastname_user;
    }

    public void setLastname_user(String lastname_user) {
        this.lastname_user = lastname_user;
    }

    public String getMail_user() {
        return mail_user;
    }

    public void setMail_user(String mail_user) {
        this.mail_user = mail_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getTel_user() {
        return tel_user;
    }

    public void setTel_user(String tel_user) {
        this.tel_user = tel_user;
    }
}
