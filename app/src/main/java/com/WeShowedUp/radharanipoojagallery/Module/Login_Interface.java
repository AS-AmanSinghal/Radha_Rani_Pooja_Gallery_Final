package com.WeShowedUp.radharanipoojagallery.Module;

import com.WeShowedUp.radharanipoojagallery.Response.ContactResponse.ContactResponse;
import com.WeShowedUp.radharanipoojagallery.Response.LoginResponse.LoginResponse;
import com.WeShowedUp.radharanipoojagallery.Response.RegistrationResponse.RegistrationResponse;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @GET("posts/{phone}")
    Call<JsonObject> post(
            @Path("phone") String phone
    );

    @GET("coupons/{id}")
    Call<JsonObject> coupon(
            @Path("id") String phone
    );

    @GET("getFeed")
    Call<JsonObject> feed();

    @FormUrlEncoded
    @POST("CTCTacts")
    Call<ContactResponse> contact(
            @Field("ctc") ArrayList<String> ctc
    );
}
