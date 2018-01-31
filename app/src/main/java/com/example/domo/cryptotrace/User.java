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
       @SerializedName("percent_change_1h")
    @Expose
    private String percentChange1h;
    @SerializedName("percent_change_24h")
    @Expose
    private String percentChange24h;
    @SerializedName("percent_change_7d")
    @Expose
    private String percentChange7d;


    public User() {
    }


    public User(String name, String rank, String symbol, String price, String percentChange1h, String percentChange24h,String percentChange7d) {
        super();
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.price=price;
        this.percentChange1h=percentChange1h;
        this.percentChange24h=percentChange24h;
        this.percentChange7d=percentChange7d;
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

    public void setSymbol(String symbol) {
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

    public void setPrice(String price) {
        this.price = price;
    }
    public String getPercentChange1h() {
        return percentChange1h;
    }

    public void setpercentChange1h(String percentChange1h) {
        this.percentChange1h = percentChange1h;
    }
    public String getPercentChange24h() {
        return percentChange24h;
    }

    public void setpercentChange24h(String percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public String getPercentChange7d() {
        return percentChange7d;
    }

    public void setpercentChange7d(String percentChange7d) {
        this.percentChange7d = percentChange7d;
    }




}