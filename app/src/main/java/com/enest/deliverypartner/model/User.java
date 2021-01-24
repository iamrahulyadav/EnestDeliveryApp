package com.enest.deliverypartner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("email")
    @Expose
    private final String email = "";
    @SerializedName("mobile")
    @Expose
    private final String mobile = "";
    @SerializedName("password")
    @Expose
    private final String password = "";
    @SerializedName("address")
    @Expose
    private final String address = "";
    @SerializedName("area")
    @Expose
    private final String area = "";
    @SerializedName("status")
    @Expose
    private final String status = "";
    @SerializedName("id")
    @Expose
    private String id = "";
    @SerializedName("name")
    @Expose
    private String name = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getaddress() {
        return address;
    }

    public String getArea() {
        return area;
    }

    public String getStatus() {
        return status;
    }

}
