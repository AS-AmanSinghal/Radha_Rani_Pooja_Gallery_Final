
package com.WeShowedUp.radharanipoojagallery.Response.UploadResponse;

import com.google.gson.annotations.SerializedName;

public class UploadResponse {

    @SerializedName("error")
    private String mError;

    public String getError() {
        return mError;
    }

    public void setError(String error) {
        mError = error;
    }

}
