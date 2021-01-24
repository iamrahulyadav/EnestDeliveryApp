package com.enest.deliverypartner.fregment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enest.deliverypartner.R;
import com.enest.deliverypartner.activity.OrderDeliverDetailsActivity;
import com.enest.deliverypartner.model.PendingOrder;
import com.enest.deliverypartner.model.PendingOrderItem;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

import static com.enest.deliverypartner.utils.SessionManager.currncy;

@SuppressLint("NonConstantResourceId")
public class DeliveryFragment extends Fragment implements GetResult.MyListener {
    @BindView(R.id.txt_itmecount)
    TextView txtItmecount;
    @BindView(R.id.txt_nodata)
    TextView txtNodata;
    @BindView(R.id.recycle_delivery)
    RecyclerView recycleDelivery;
    CustPrograssbar custPrograssbar;
    SessionManager sessionManager;
    User user;
    List<PendingOrderItem> pendinglistMain = new ArrayList<>();
    PendingAdepter myOrderAdepter;

    public DeliveryFragment() {
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
        View view = inflater.inflate(R.layout.fragment_delivery, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getActivity());
        recycleDelivery.setLayoutManager(recyclerLayoutManager);

        custPrograssbar = new CustPrograssbar();
        sessionManager = new SessionManager(getActivity());
        user = sessionManager.getUserDetails();

        getCumpletOrder();
        return view;
    }

    private void getCumpletOrder() {
        custPrograssbar.PrograssCreate(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("rid", user.getId());
            JsonParser jsonParser = new JsonParser();

            Call<JsonObject> call = APIClient.getInterface().getComplete((JsonObject) jsonParser.parse(jsonObject.toString()));
            GetResult getResult = new GetResult();
            getResult.setMyListener(this);
            getResult.callForLogin(call, "1");

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
                PendingOrder pendingOrder = gson.fromJson(result.toString(), PendingOrder.class);
                if (pendingOrder.getResult().equalsIgnoreCase("true")) {
                    txtItmecount.setText(pendingOrder.getOrderData().size() + " Orders");
                    if (pendingOrder.getOrderData().size() == 0) {
                        txtNodata.setVisibility(View.VISIBLE);
                    } else {
                        pendinglistMain = pendingOrder.getOrderData();
                        myOrderAdepter = new PendingAdepter(pendinglistMain);
                        recycleDelivery.setAdapter(myOrderAdepter);
                    }
                } else {
                    txtNodata.setVisibility(View.VISIBLE);
                }


            }

        } catch (Exception ignored) {
        }
    }

    public class PendingAdepter extends RecyclerView.Adapter<PendingAdepter.ViewHolder> {
        private final List<PendingOrderItem> pendinglist;

        public PendingAdepter(List<PendingOrderItem> pendinglist) {
            this.pendinglist = pendinglist;
        }

        @NotNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.deliveri_item, parent, false);
            return new ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(PendingAdepter.ViewHolder holder,
                                     int position) {
            Log.e("position", "" + position);
            PendingOrderItem order = pendinglist.get(position);
            holder.txtOderid.setText("OrderID #" + order.getOrderid());
            holder.txtDateandstatus.setText(order.getStatus() + " on " + order.getOdate());
            holder.txtType.setText("" + order.getPMethod());
            holder.txtPricetotla.setText(sessionManager.getStringData(currncy) + " " + order.getTotal());
            holder.lvlClick.setOnClickListener(v -> startActivity(new Intent(getActivity(), OrderDeliverDetailsActivity.class).putExtra("MyClass", order).putParcelableArrayListExtra("MyList", order.getProductinfo())));
        }

        @Override
        public int getItemCount() {
            return pendinglist.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.txt_oderid)
            TextView txtOderid;
            @BindView(R.id.txt_pricetotla)
            TextView txtPricetotla;
            @BindView(R.id.txt_dateandstatus)
            TextView txtDateandstatus;
            @BindView(R.id.txt_type)
            TextView txtType;

            @BindView(R.id.lvl_click)
            LinearLayout lvlClick;
            @BindView(R.id.img_right)
            ImageView imgRight;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
