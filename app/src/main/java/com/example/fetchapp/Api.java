package com.example.fetchapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://fetch-hiring.s3.amazonaws.com/";
    @GET("hiring.json")
    Call<List<JItems>> getJItems();
}
