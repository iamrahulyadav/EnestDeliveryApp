package com.enest.deliverypartner.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class CustPrograssbar {

    ProgressDialog progressDialog;

    public void PrograssCreate(Context context) {
        try {
            if (progressDialog == null || !progressDialog.isShowing()) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Progress...");
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ClosePrograssBar() {
        if (progressDialog != null) {
            try {
                progressDialog.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
