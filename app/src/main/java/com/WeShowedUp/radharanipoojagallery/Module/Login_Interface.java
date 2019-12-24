package com.WeShowedUp.radharanipoojagallery.Module;

import com.WeShowedUp.radharanipoojagallery.Response.ContactResponse.ContactResponse;
import com.WeShowedUp.radharanipoojagallery.Response.GenerateCouponResponse.GenerateCouponResponse;
import com.WeShowedUp.radharanipoojagallery.Response.GetCouponResponse.GetCouponResponse;
import com.WeShowedUp.radharanipoojagallery.Response.LoginResponse.LoginResponse;
import com.WeShowedUp.radharanipoojagallery.Response.RegistrationResponse.RegistrationResponse;
import com.WeShowedUp.radharanipoojagallery.Response.UploadResponse.UploadResponse;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @FormUrlEncoded
    @POST("makecoupon")
    Call<GenerateCouponResponse> generatecoupen(
            @Field("phone") String phone,
            @Field("postId") String post_id
    );

    @GET("getCouponId")
    Call<GetCouponResponse> getcoupon(
            @Query("postId") String post_id
    );

    @Multipart
    @POST("uploadpost")
    Call<UploadResponse> upload(
            @Part MultipartBody.Part image,
            @Part("u_id") String phone,
            @Part("caption") String caption,
            @Part("amount") String amount
    );
}
