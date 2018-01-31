package com.example.domo.cryptotrace;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Domo on 31.1.2018..
 */

public interface APIService {
    @GET("ticker")
    Call<List<User>> getUserData();
}