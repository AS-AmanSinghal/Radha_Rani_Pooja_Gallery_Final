package com.WeShowedUp.radharanipoojagallery.Module;

import com.WeShowedUp.radharanipoojagallery.Response.LoginResponse.LoginResponse;
import com.WeShowedUp.radharanipoojagallery.Response.RegistrationResponse.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Login_Interface
{
    String BASE_URL="http://radharani.co.in/api/";

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("phone") String phone,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("signup")
    Call<RegistrationResponse> registration(
            @Field("phone") String phone,
            @Field("password") String password
    );
}
