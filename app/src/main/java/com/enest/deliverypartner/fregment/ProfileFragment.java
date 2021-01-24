package com.enest.deliverypartner.fregment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.enest.deliverypartner.R;
import com.enest.deliverypartner.activity.InfoActivity;
import com.enest.deliverypartner.activity.ProfileActivity;
import com.enest.deliverypartner.model.Ostatus;
import com.enest.deliverypartner.model.RestResponse;
import com.enest.deliverypartner.model.User;
import com.enest.deliverypartner.retrofit.APIClient;
import com.enest.deliverypartner.retrofit.GetResult;
import com.enest.deliverypartner.utils.CustPrograssbar;
import com.enest.deliverypartner.utils.SessionManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

@SuppressLint("NonConstantResourceId")
public class ProfileFragment extends Fragment implements GetResult.MyListener {

    @BindView(R.id.img_logout)
    ImageView imgLogout;
    @BindView(R.id.ed_username)
    TextView edUsername;
    @BindView(R.id.ed_email)
    TextView edEmail;
    @BindView(R.id.ed_phone)
    TextView edPhone;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @BindView(R.id.switch1)
    Switch switch1;
    @BindView(R.id.txt_complet)
    TextView txtComplet;
    @BindView(R.id.txt_sale)
    TextView txtSale;
    @BindView(R.id.txt_cencel)
    TextView txtCencel;
    @BindView(R.id.txt_status)
    TextView txtStatus;
    @BindView(R.id.img_edit)
    ImageView imgEdit;
    SessionManager sessionManager;
    User user;
    CustPrograssbar custPrograssbar;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        ProfileActivity.listener = this;
        sessionManager = new SessionManager(getActivity());
        custPrograssbar = new CustPrograssbar();
        user = sessionManager.getUserDetails();
        edUsername.setText("" + user.getName());
        edEmail.setText("" + user.getEmail());
        edPhone.setText("" + user.getMobile());

        if (sessionManager.getBooleanData("status")) {
            txtStatus.setText("Avaliable");
            switch1.setChecked(true);
        } else {
            switch1.setChecked(false);
            txtStatus.setText("Not Avaliable");
        }
        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                status("1");
                txtStatus.setText("Avaliable");
            } else {
                status("0");
                txtStatus.setText("Not Avaliable");

            }
        });
        orderStatus();
        return view;
    }

    private void status(String key) {
        custPrograssbar.PrograssCreate(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", user.getId());
            jsonObject.put("status", key);
            JsonParser jsonParser = new JsonParser();

            Call<JsonObject> call = APIClient.getInterface().getStatus((JsonObject) jsonParser.parse(jsonObject.toString()));
            GetResult getResult = new GetResult();
            getResult.setMyListener(this);
            getResult.callForLogin(call, "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void orderStatus() {
        custPrograssbar.PrograssCreate(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("rid", user.getId());

            JsonParser jsonParser = new JsonParser();

            Call<JsonObject> call = APIClient.getInterface().orderStatus((JsonObject) jsonParser.parse(jsonObject.toString()));
            GetResult getResult = new GetResult();
            getResult.setMyListener(this);
            getResult.callForLogin(call, "2");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void callback(JsonObject result, String callNo) {
        try {
            custPrograssbar.ClosePrograssBar();
            if (callNo.equalsIgnoreCase("1")) {
                Gson gson = new Gson();
                RestResponse response = gson.fromJson(result.toString(), RestResponse.class);
                if (response.getResult().equalsIgnoreCase("true")) {
                    sessionManager.setBooleanData("status", switch1.isChecked());
                }
            } else if (callNo.equalsIgnoreCase("2")) {
                Gson gson = new Gson();
                Ostatus ostatus = gson.fromJson(result.toString(), Ostatus.class);
                txtComplet.setText("" + ostatus.getOrderData().getTotalCompleteOrder());
                txtSale.setText(sessionManager.getStringData(SessionManager.currncy) + " " + ostatus.getOrderData().getTotalSale());
                txtCencel.setText("" + ostatus.getOrderData().getTotalRejectOrder());
                if (ostatus.getOrderData().getRiderStatus().equalsIgnoreCase("1")) {
                    switch1.setChecked(true);
                    sessionManager.setBooleanData("status", true);
                } else {
                    switch1.setChecked(false);
                    sessionManager.setBooleanData("status", false);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.img_logout)
    public void onClick() {

    }

    @SuppressLint("SetTextI18n")
    public void onrefaress() {
        if (user != null && sessionManager != null) {
            user = sessionManager.getUserDetails();
            edUsername.setText("" + user.getName());
            edEmail.setText("" + user.getEmail());
            edPhone.setText("" + user.getMobile());
        }

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @OnClick({R.id.img_edit, R.id.img_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_edit:
                startActivity(new Intent(getActivity(), ProfileActivity.class));
                break;
            case R.id.img_logout:
                @SuppressLint("SetTextI18n") AlertDialog myDelete = new AlertDialog.Builder(getActivity())
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to logout")
                        .setIcon(R.drawable.ic_logout)

                        .setPositiveButton("Logout", (dialog, whichButton) -> {
                            //your deleting code
                            dialog.dismiss();
                            sessionManager.logoutUser();
                            status("0");
                            txtStatus.setText("Not Avaliable");
                            startActivity(new Intent(getActivity(), InfoActivity.class));
                            getActivity().finish();
                        })
                        .setNegativeButton("cancel", (dialog, which) -> dialog.dismiss())
                        .create();
                myDelete.show();
                break;
            default:
                break;
        }
    }
}
