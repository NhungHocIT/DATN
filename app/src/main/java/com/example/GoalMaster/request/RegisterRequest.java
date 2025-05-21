package com.example.GoalMaster.request;

public class RegisterRequest {
    private String emailPhone;  // Email hoặc số điện thoại
    private String password;
    private String confirmPassword;// Mật khẩu
    private String username;    // Tên người dùng
    private String birth;       // Ngày sinh (định dạng dd-MM-yyyy)

    // Constructor có thêm trường birth
    public RegisterRequest(String emailPhone, String password, String confirmPassword, String username, String birth) {
        this.emailPhone = emailPhone;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.username = username;
        this.birth = birth;

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


    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getConfirmPassword(){return confirmPassword;}
    public void setConfirmPassword(String confirmPassword){this.confirmPassword = confirmPassword;}

}
