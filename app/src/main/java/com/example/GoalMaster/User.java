package com.example.GoalMaster;

public class User {
    private int id;
    private String name;
    private String email;

    public User() {
    }

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return name; }
    public void setUsername(String username) { this.name = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
