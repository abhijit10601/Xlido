package com.crio.xlido.entities;

public class User {
    private final Long id;
    private final String email;
    private final String password;

    public User(String email, String password) {
        this.id = null;
        this.email = email;
        this.password = password;
    }

    public User(Long id, User other){
        this.id = id;
        this.email = other.email;
        this.password = other.password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User [id=" + id + "]";
    }  
}
