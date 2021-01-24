package com.enest.deliverypartner.fregment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enest.deliverypartner.R;
import com.enest.deliverypartner.model.Noti;
import com.enest.deliverypartner.model.NotiItem;
import com.enest.deliverypartner.model.User;
import com.enest.deliverypartner.retrofit.APIClient;
import com.enest.deliverypartner.retrofit.GetResult;
import com.enest.deliverypartner.utils.CustPrograssbar;
import com.enest.deliverypartner.utils.SessionManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

@SuppressLint("NonConstantResourceId")
public class NotificationFragment extends Fragment implements GetResult.MyListener {

    @BindView(R.id.recycle_delivery)
    RecyclerView recycleDelivery;
    @BindView(R.id.txt_nodata)
    TextView txtNodata;
    CustPrograssbar custPrograssbar;
    SessionManager sessionManager;
    User user;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getActivity());
        recycleDelivery.setLayoutManager(recyclerLayoutManager);

        custPrograssbar = new CustPrograssbar();
        sessionManager = new SessionManager(getActivity());
        user = sessionManager.getUserDetails();
        getNotification();
        return view;
    }

    private void getNotification() {
        custPrograssbar.PrograssCreate(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", user.getId());
            JsonParser jsonParser = new JsonParser();

            Call<JsonObject> call = APIClient.getInterface().getNoti((JsonObject) jsonParser.parse(jsonObject.toString()));
            GetResult getResult = new GetResult();
            getResult.setMyListener(this);
            getResult.callForLogin(call, "1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void callback(JsonObject result, String callNo) {

        try {
            custPrograssbar.ClosePrograssBar();
            Gson gson = new Gson();
            Noti noti = gson.fromJson(result.toString(), Noti.class);
            if (noti.getResult().equalsIgnoreCase("true")) {
                if (noti.getData().size() == 0) {
                    txtNodata.setVisibility(View.VISIBLE);
                } else {
                    NotificationAdepter myOrderAdepter = new NotificationAdepter(noti.getData());
                    recycleDelivery.setAdapter(myOrderAdepter);
                }
            } else {
                txtNodata.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class NotificationAdepter extends RecyclerView.Adapter<NotificationAdepter.ViewHolder> {

        private final List<NotiItem> pendinglist;

        public NotificationAdepter(List<NotiItem> pendinglist) {
            this.pendinglist = pendinglist;
        }

        @NotNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.notification_item, parent, false);
            return new ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(ViewHolder holder,
                                     int position) {
            Log.e("position", "" + position);
            NotiItem order = pendinglist.get(position);
            holder.txtTitel.setText("" + order.getMsg());
            holder.txtDate.setText(order.getDate());

        }

        @Override
        public int getItemCount() {
            return pendinglist.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.txt_titel)
            TextView txtTitel;
            @BindView(R.id.txt_date)
            TextView txtDate;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
