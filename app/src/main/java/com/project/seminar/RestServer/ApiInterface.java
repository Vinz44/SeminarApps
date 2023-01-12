package com.project.seminar.RestServer;

import com.project.seminar.Model.GetHeros;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("heros")
    Call<GetHeros> getHeros();
}
