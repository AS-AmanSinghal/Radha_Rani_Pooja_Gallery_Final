package com.WeShowedUp.radharanipoojagallery.ui.coupon;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.WeShowedUp.radharanipoojagallery.General.RetrofitClass;
import com.WeShowedUp.radharanipoojagallery.Lists.Coupon;
import com.WeShowedUp.radharanipoojagallery.R;
import com.WeShowedUp.radharanipoojagallery.Response.CouponResponse.Datum;
import com.google.gson.JsonObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CouponFragment extends Fragment
{
    ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_coupon, container, false);
        imageView=root.findViewById(R.id.coupon_qr_code);
        return root;
    }
private RecyclerView recyclerView;
    private CouponViewModel adapter;
    private List<Datum> couponList=new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycler_coupon);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(),RecyclerView.VERTICAL,false));
        new MyCoupons().execute();
        adapter=new CouponViewModel(getContext(),couponList);
        recyclerView.setAdapter(adapter);
    }

    public class MyCoupons extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {
          //Toast.makeText(getContext(),getActivity().getIntent().getStringExtra("phone"),Toast.LENGTH_SHORT).show();
            RetrofitClass retrofitClass=new RetrofitClass();
            Call<JsonObject> call=retrofitClass.retrofit().coupon(getActivity().getIntent().getStringExtra("phone"));
            call.enqueue(new Callback<JsonObject>()
            {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response)
                {
                    if (response.body()!=null)
                    {
                        JsonObject jsonObject=response.body();
                        try {
                            JSONObject jsonObject1 = new JSONObject(String.valueOf(jsonObject));
                            JSONArray jsonArray=jsonObject1.getJSONArray("data");
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                Datum datum=new Datum();
                                JSONObject jsonObject2=jsonArray.getJSONObject(i);
                                datum.setId(jsonObject2.optLong("id"));
                                datum.setPostId(jsonObject2.getString("postId"));
                                datum.setAmount(jsonObject2.optLong("amount"));
                                datum.setStart(jsonObject2.optString("start"));
                                datum.setEnd(jsonObject2.optString("end"));
                                datum.setStatus(jsonObject2.getLong("status"));
                                couponList.add(datum);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter=new CouponViewModel(getContext(),couponList);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });

            return null;
        }
    }
}