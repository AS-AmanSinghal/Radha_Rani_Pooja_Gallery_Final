
package com.WeShowedUp.radharanipoojagallery.Response.ContactResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactResponse {

    @SerializedName("data")
    private List<Object> mData;
    @SerializedName("flag")
    private Boolean mFlag;
    @SerializedName("message")
    private String mMessage;

    public List<Object> getData() {
        return mData;
    }

    public void setData(List<Object> data) {
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
