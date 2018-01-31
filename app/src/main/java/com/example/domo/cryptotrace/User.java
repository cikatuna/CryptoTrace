package com.example.domo.cryptotrace;

/**
 * Created by Domo on 31.1.2018..
 */

public class User {

    private String name;
    private String rank;


    public User() {
    }


    public User(String name, String hobby) {
        super();
        this.name = name;
        this.rank = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}