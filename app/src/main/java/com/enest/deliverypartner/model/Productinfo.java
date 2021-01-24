package com.enest.deliverypartner.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Productinfo implements Parcelable {

    public static final Creator<Productinfo> CREATOR = new Creator<Productinfo>() {
        @Override
        public Productinfo createFromParcel(Parcel in) {
            return new Productinfo(in);
        }

        @Override
        public Productinfo[] newArray(int size) {
            return new Productinfo[size];
        }
    };
    @SerializedName("discount")
    private final String mDiscount;
    @SerializedName("product_image")
    private final String mProductImage;
    @SerializedName("product_name")
    private final String mProductName;
    @SerializedName("product_price")
    private final String mProductPrice;
    @SerializedName("product_qty")
    private final String mProductQty;
    @SerializedName("product_weight")
    private final String mProductWeight;

    protected Productinfo(Parcel in) {
        mDiscount = in.readString();
        mProductImage = in.readString();
        mProductName = in.readString();
        mProductPrice = in.readString();
        mProductQty = in.readString();
        mProductWeight = in.readString();
    }

    public String getDiscount() {
        return mDiscount;
    }

    public String getProductImage() {
        return mProductImage;
    }

    public String getProductName() {
        return mProductName;
    }

    public String getProductPrice() {
        return mProductPrice;
    }

    public String getProductQty() {
        return mProductQty;
    }

    public String getProductWeight() {
        return mProductWeight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDiscount);
        dest.writeString(mProductImage);
        dest.writeString(mProductName);
        dest.writeString(mProductPrice);
        dest.writeString(mProductQty);
        dest.writeString(mProductWeight);
    }
}
