package com.sgs.mnim2_test;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class API {

    private static API instance;

    private static final String baseUrl = "https://api.github.com/";
    public static APII api;

    private API(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(APII.class);
    };

    public static API getInstance(){
        if(instance==null)
            return new API();
        return instance;
    }

}
