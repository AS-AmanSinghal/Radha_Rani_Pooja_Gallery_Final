
package com.WeShowedUp.radharanipoojagallery.Response.MyPhotoResponse;

import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("creation_time")
    private String mCreationTime;
    @SerializedName("id")
    private String mId;
    @SerializedName("likes")
    private Long mLikes;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("price")
    private Long mPrice;
    @SerializedName("shares")
    private Long mShares;
    @SerializedName("status")
    private Long mStatus;
    @SerializedName("u_id")
    private String mUId;
    @SerializedName("url")
    private String mUrl;

    public String getCreationTime() {
        return mCreationTime;
    }

    public void setCreationTime(String creationTime) {
        mCreationTime = creationTime;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Long getLikes() {
        return mLikes;
    }

    public void setLikes(long likes) {
        mLikes = likes;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Long getPrice() {
        return mPrice;
    }

    public void setPrice(Long price) {
        mPrice = price;
    }

    public Long getShares() {
        return mShares;
    }

    public void setShares(Long shares) {
        mShares = shares;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

    public String getUId() {
        return mUId;
    }

    public void setUId(String uId) {
        mUId = uId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}
