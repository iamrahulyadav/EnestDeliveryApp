package com.enest.deliverypartner.model;

import com.google.gson.annotations.SerializedName;

public class LoginUser {

    @SerializedName("ResponseCode")
    private String mResponseCode;
    @SerializedName("ResponseMsg")
    private String mResponseMsg;
    @SerializedName("Result")
    private String mResult;
    @SerializedName("rider")
    private User mUser;
    @SerializedName("d_charge")
    private int dCharge;

    @SerializedName("currency")
    private String currency;

    public int getdCharge() {
        return dCharge;
    }

    public String getResponseMsg() {
        return mResponseMsg;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
    }

    public User getUser() {
        return mUser;
    }

    public String getCurrency() {
        return currency;
    }

}
