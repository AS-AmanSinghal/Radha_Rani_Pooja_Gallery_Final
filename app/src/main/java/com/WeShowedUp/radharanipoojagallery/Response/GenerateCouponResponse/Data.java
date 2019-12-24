
package com.WeShowedUp.radharanipoojagallery.Response.GenerateCouponResponse;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("amount")
    private Long mAmount;
    @SerializedName("end")
    private String mEnd;
    @SerializedName("id")
    private Long mId;
    @SerializedName("start")
    private String mStart;
    @SerializedName("status")
    private Long mStatus;

    public Long getAmount() {
        return mAmount;
    }

    public void setAmount(Long amount) {
        mAmount = amount;
    }

    public String getEnd() {
        return mEnd;
    }

    public void setEnd(String end) {
        mEnd = end;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getStart() {
        return mStart;
    }

    public void setStart(String start) {
        mStart = start;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

}
