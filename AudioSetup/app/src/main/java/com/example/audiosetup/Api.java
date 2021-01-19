package com.example.audiosetup;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api{
    @GET("/setSpeed/{speed}")
    Call<Void> setSpeed(@Path("speed") Integer speed  );
}