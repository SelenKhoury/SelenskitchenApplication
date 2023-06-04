package com.example.selenskitchenapplication;

public class User {

    private String username, email, password;

    public User(){

    }

    public User(String username,String password , String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

