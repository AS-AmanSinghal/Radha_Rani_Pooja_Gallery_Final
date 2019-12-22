
package com.WeShowedUp.radharanipoojagallery.Response.LoginResponse;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("password")
    private String mPassword;
    @SerializedName("phone")
    private String mPhone;

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

}
