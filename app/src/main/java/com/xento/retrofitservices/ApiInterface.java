package com.xento.retrofitservices;


import com.google.gson.JsonObject;
import com.xento.model.RootObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("info/all.json")
    Call<RootObject> getCountriesCall();
}
