package com.example.gamelog;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String id;
    public String name;
    public String email;
    public String password;
    public List<String> reviewIds;

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.reviewIds = new ArrayList<>();
    }
}
