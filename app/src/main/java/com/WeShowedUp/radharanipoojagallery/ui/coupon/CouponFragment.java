package com.WeShowedUp.radharanipoojagallery.ui.coupon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.WeShowedUp.radharanipoojagallery.Lists.Coupon;
import com.WeShowedUp.radharanipoojagallery.R;

import java.util.ArrayList;
import java.util.List;


public class CouponFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_coupon, container, false);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        couponViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
private RecyclerView recyclerView;
    private CouponViewModel adapter;
    private List<Coupon> couponList=new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycler_coupon);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(),RecyclerView.VERTICAL,false));
        getCoupon();
        adapter=new CouponViewModel(getContext(),couponList);
        recyclerView.setAdapter(adapter);
    }
    public void getCoupon()
    {
        Coupon coupon1=new Coupon();
        couponList.add(coupon1);
        couponList.add(coupon1);
        couponList.add(coupon1);
        couponList.add(coupon1);
        couponList.add(coupon1);
        couponList.add(coupon1);
        couponList.add(coupon1);
        couponList.add(coupon1);
        couponList.add(coupon1);
        couponList.add(coupon1);
        couponList.add(coupon1);
        adapter=new CouponViewModel(getContext(),couponList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}