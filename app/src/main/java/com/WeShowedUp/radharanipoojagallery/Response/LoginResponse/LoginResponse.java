
package com.WeShowedUp.radharanipoojagallery.Response.LoginResponse;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("flag")
    private Boolean mFlag;

    public Boolean getFlag() {
        return mFlag;
    }

    public void setFlag(Boolean flag) {
        mFlag = flag;
    }

}
