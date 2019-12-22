
package com.WeShowedUp.radharanipoojagallery.Response.LoginResponse;


import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("data")
    private Data mData;
    @SerializedName("flag")
    private Boolean mFlag;

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

    public Boolean getFlag() {
        return mFlag;
    }

    public void setFlag(Boolean flag) {
        mFlag = flag;
    }

}
