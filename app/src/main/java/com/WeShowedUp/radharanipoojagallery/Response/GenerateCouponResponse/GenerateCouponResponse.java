
package com.WeShowedUp.radharanipoojagallery.Response.GenerateCouponResponse;

import com.google.gson.annotations.SerializedName;

public class GenerateCouponResponse {

    @SerializedName("data")
    private Data mData;
    @SerializedName("flag")
    private Boolean mFlag;
    @SerializedName("message")
    private String mMessage;

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

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}
