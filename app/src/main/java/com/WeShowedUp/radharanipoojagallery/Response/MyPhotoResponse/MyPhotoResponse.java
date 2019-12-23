
package com.WeShowedUp.radharanipoojagallery.Response.MyPhotoResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyPhotoResponse {

    @SerializedName("data")
    private List<Datum> mData;
    @SerializedName("flag")
    private Boolean mFlag;
    @SerializedName("message")
    private String mMessage;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
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
