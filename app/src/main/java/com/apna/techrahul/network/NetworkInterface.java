package com.apna.techrahul.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkInterface {
    public String Url="https://jsonplaceholder.typicode.com/";
    @GET("posts")
    Call<List<RecycleNetwork>> getData();
}
