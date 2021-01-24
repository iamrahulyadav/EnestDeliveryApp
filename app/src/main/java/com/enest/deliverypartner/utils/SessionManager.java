package com.enest.deliverypartner.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.enest.deliverypartner.model.User;
import com.google.gson.Gson;

public class SessionManager {
    public static String userdata = "riderdata";
    public static String currncy = "currncy";
    private final SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mPrefs.edit();
    }

    public void setStringData(String key, String val) {
        mEditor.putString(key, val);
        mEditor.commit();
    }

    public String getStringData(String key) {
        return mPrefs.getString(key, "");
    }

    public void setBooleanData(String key, Boolean val) {
        mEditor.putBoolean(key, val);
        mEditor.commit();
    }

    public boolean getBooleanData(String key) {
        return mPrefs.getBoolean(key, false);
    }

    public void setIntData(String key, int val) {
        mEditor.putInt(key, val);
        mEditor.commit();
    }

    public User getUserDetails() {
        return new Gson().fromJson(mPrefs.getString(userdata, ""), User.class);
    }

    public void setUserDetails(User val) {
        mEditor.putString(userdata, new Gson().toJson(val));
        mEditor.commit();
    }

    public void logoutUser() {
        mEditor.clear();
        mEditor.commit();
    }
}
