package com.example.GoalMaster.response;

public class RegisterResponse {
    private String message;     // Thông báo từ server
    private int statusCode;     // Mã trạng thái (200, 400, ...)
    private boolean status;     // Trạng thái thành công hay không

    // Getter & Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
