package com.WeShowedUp.radharanipoojagallery.General;

import com.WeShowedUp.radharanipoojagallery.Module.Login_Interface;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass
{
    public Login_Interface retrofit()
    {

        Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl(Login_Interface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Login_Interface.class);
    }
}
