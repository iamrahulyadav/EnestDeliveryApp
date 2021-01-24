package com.enest.deliverypartner.model;

import com.google.gson.annotations.SerializedName;

public class OrderData {

    @SerializedName("total_complete_order")
    private String mTotalCompleteOrder;
    @SerializedName("total_reject_order")
    private String mTotalRejectOrder;
    @SerializedName("total_sale")
    private String mTotalSale;
    @SerializedName("rider_status")
    private String riderStatus;


    public String getTotalCompleteOrder() {
        return mTotalCompleteOrder;
    }

    public String getTotalRejectOrder() {
        return mTotalRejectOrder;
    }

    public String getTotalSale() {
        return mTotalSale;
    }

    public String getRiderStatus() {
        return riderStatus;
    }

}
