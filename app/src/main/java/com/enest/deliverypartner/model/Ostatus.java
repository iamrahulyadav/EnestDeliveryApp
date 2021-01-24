package com.enest.deliverypartner.model;

import com.google.gson.annotations.SerializedName;


public class Ostatus {

    @SerializedName("order_data")
    private OrderData mOrderData;
    @SerializedName("Result")
    private String mResult;

    public OrderData getOrderData() {
        return mOrderData;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
    }

}
