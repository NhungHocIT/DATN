package com.example.GoalMaster.request;

public class RegisterRequest {
    private String emailPhone;  // Email hoặc số điện thoại
    private String password;    // Mật khẩu
    private String username;    // Tên người dùng

    // Constructor
    public RegisterRequest(String emailPhone, String password, String username) {
        this.emailPhone = emailPhone;
        this.password = password;
        this.username = username;
    }

    // Getter & Setter
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
