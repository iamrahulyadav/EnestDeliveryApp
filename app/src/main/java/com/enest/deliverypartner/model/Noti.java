package com.enest.deliverypartner.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Noti {

    @SerializedName("data")
    private List<NotiItem> mData;
    @SerializedName("ResponseCode")
    private String mResponseCode;
    @SerializedName("Result")
    private String mResult;

    public List<NotiItem> getData() {
        return mData;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
    }

}
