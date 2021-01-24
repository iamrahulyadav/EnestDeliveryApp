package com.enest.deliverypartner;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.onesignal.OneSignal;


public class MyApplication extends Application {
    private static final String ONESIGNAL_APP_ID = "e389483a-d79a-4dde-b76b-356af4900aea";
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}