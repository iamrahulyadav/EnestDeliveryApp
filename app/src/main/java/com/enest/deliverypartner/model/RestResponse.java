package com.enest.deliverypartner.model;

import com.google.gson.annotations.SerializedName;

public class RestResponse {
    @SerializedName("ResponseMsg")
    private String mResponseMsg;
    @SerializedName("Result")
    private String mResult;

    public String getResponseMsg() {
        return mResponseMsg;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
    }

}
