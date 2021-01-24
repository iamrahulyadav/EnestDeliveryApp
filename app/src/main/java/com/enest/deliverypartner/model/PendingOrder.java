package com.enest.deliverypartner.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PendingOrder {

    @SerializedName("order_data")
    private List<PendingOrderItem> mOrderData;
    @SerializedName("Result")
    private String mResult;

    public List<PendingOrderItem> getOrderData() {
        return mOrderData;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
    }

}
