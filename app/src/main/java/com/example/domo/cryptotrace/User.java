package com.example.domo.cryptotrace;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Domo on 31.1.2018..
 */

public class User {

    private String name;
    private String symbol;
    private String rank;

    @SerializedName("price_usd")
    @Expose
    private String price;


    public User() {
    }


    public User(String name, String rank) {
        super();
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String name) {
        this.symbol = symbol;
    }


    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String rank) {
        this.price = price;
    }


}