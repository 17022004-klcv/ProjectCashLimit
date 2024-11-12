package com.example.cashlimit.model;

public class Loggeo {

    private String user_loggeo;
    private String password_loggeo;

    public Loggeo() {
    }

    public Loggeo(String user_loggeo, String password_loggeo) {
        this.user_loggeo = user_loggeo;
        this.password_loggeo = password_loggeo;
    }

    public String getUser_loggeo() {
        return user_loggeo;
    }

    public void setUser_loggeo(String user_loggeo) {
        this.user_loggeo = user_loggeo;
    }

    public String getPassword_loggeo() {
        return password_loggeo;
    }

    public void setPassword_loggeo(String password_loggeo) {
        this.password_loggeo = password_loggeo;
    }
}
