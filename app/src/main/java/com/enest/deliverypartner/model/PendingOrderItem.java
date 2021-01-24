package com.enest.deliverypartner.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PendingOrderItem implements Parcelable {

    public static final Creator<PendingOrderItem> CREATOR = new Creator<PendingOrderItem>() {
        @Override
        public PendingOrderItem createFromParcel(Parcel in) {
            return new PendingOrderItem(in);
        }

        @Override
        public PendingOrderItem[] newArray(int size) {
            return new PendingOrderItem[size];
        }
    };
    @SerializedName("astatus")
    private final String mAstatus;
    @SerializedName("d_charge")
    private final String mDCharge;
    @SerializedName("delivery")
    private final String mDelivery;
    @SerializedName("email")
    private final String mEmail;
    @SerializedName("mobile")
    private final String mMobile;
    @SerializedName("odate")
    private final String mOdate;
    @SerializedName("pickup")
    private final String mPickup;
    @SerializedName("timesloat")
    private final String mTimesloat;
    @SerializedName("total")
    private final String mTotal;
    @SerializedName("sign")
    private final String sign;
    @SerializedName("orderid")
    private final String orderid;
    @SerializedName("name")
    private String mName;
    @SerializedName("p_method")
    private String mPMethod;
    @SerializedName("productinfo")
    private ArrayList<Productinfo> mProductinfo;
    @SerializedName("status")
    private String mStatus;

    protected PendingOrderItem(Parcel in) {
        mAstatus = in.readString();
        mDCharge = in.readString();
        mDelivery = in.readString();
        mEmail = in.readString();
        mMobile = in.readString();
        mName = in.readString();
        mOdate = in.readString();
        mPMethod = in.readString();
        mPickup = in.readString();
        mStatus = in.readString();
        mTimesloat = in.readString();
        mTotal = in.readString();
        orderid = in.readString();
        sign = in.readString();
    }

    public String getOrderid() {
        return orderid;
    }

    public String getDelivery() {
        return mDelivery;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getMobile() {
        return mMobile;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getOdate() {
        return mOdate;
    }

    public String getPMethod() {
        if (mPMethod.equalsIgnoreCase("Cash on delivery")) {
            mPMethod = "COD";
        }
        return mPMethod;
    }

    public ArrayList<Productinfo> getProductinfo() {
        return mProductinfo;
    }

    public void setProductinfo(ArrayList<Productinfo> productinfo) {
        mProductinfo = productinfo;
    }

    public String getStatus() {
        return mStatus.substring(0, 1).toUpperCase() + mStatus.substring(1).toLowerCase();
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getTimesloat() {
        return mTimesloat;
    }

    public String getTotal() {
        return mTotal;
    }

    public String getSign() {
        return sign;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAstatus);
        dest.writeString(mDCharge);
        dest.writeString(mDelivery);
        dest.writeString(mEmail);
        dest.writeString(mMobile);
        dest.writeString(mName);
        dest.writeString(mOdate);
        dest.writeString(mPMethod);
        dest.writeString(mPickup);
        dest.writeString(mStatus);
        dest.writeString(mTimesloat);
        dest.writeString(mTotal);
        dest.writeString(orderid);
        dest.writeString(sign);
    }
}
