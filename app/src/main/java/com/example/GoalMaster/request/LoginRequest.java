package com.example.GoalMaster.request;

public class LoginRequest {
    private String emailPhone;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String emailPhone, String password) {
        this.emailPhone = emailPhone;
        this.password = password;
    }

    // getter và setter
    public String getEmailPhone() {
        return emailPhone;
    }

    public void setEmailPhone(String emailPhone) {
        this.emailPhone = emailPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

