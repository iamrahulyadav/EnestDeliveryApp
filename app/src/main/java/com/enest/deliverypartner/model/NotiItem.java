package com.enest.deliverypartner.model;

import com.google.gson.annotations.SerializedName;

public class NotiItem {

    @SerializedName("date")
    private String mDate;
    @SerializedName("id")
    private String mId;
    @SerializedName("msg")
    private String mMsg;

    public String getDate() {
        return mDate;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getMsg() {
        return mMsg;
    }

}
