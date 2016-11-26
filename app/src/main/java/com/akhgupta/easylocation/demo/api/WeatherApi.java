package com.akhgupta.easylocation.demo.api;



import com.akhgupta.easylocation.demo.pojo.Example;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherApi {
    @GET
    Call<Example> getAllData(@Url String url);

}
