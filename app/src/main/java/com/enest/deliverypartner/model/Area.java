package com.enest.deliverypartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Area {

    @SerializedName("data")
    @Expose
    private final List<AreaD> data = null;
    @SerializedName("Result")
    @Expose
    private String result;

    public List<AreaD> getData() {
        return data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
