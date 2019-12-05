package com.WeShowedUp.radharanipoojagallery.ui.coupon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.WeShowedUp.radharanipoojagallery.Controller.CouponActivity;
import com.WeShowedUp.radharanipoojagallery.Lists.Coupon;
import com.WeShowedUp.radharanipoojagallery.R;

import java.util.List;

public class CouponViewModel extends RecyclerView.Adapter<CouponViewModel.ViewHolder> {
   private LayoutInflater inflater;
   private Context context;
   private List<Coupon> Mycoupon;

    public CouponViewModel(Context context, List<Coupon> coupon) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        Mycoupon=coupon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.card_coupon,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
         holder.cno.setText("1");
         holder.fdate.setText("15-09-2019");
         holder.ftime.setText("16:48");
         holder.tdate.setText("18-09-2019");
         holder.ttime.setText("17:40");
         holder.amn.setText("Rs 100.00");
         holder.coupen_card.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 context.startActivity(new Intent(context, CouponActivity.class));
             }
         });
    }

    @Override
    public int getItemCount() {
        return Mycoupon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView cno,fdate,ftime,tdate,ttime,amn;
        private LinearLayout coupen_card;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            cno=itemView.findViewById(R.id.redeemed_coupon_no);
            fdate=itemView.findViewById(R.id.from_date);
            ftime=itemView.findViewById(R.id.from_time);
            tdate=itemView.findViewById(R.id.to_date);
            ttime=itemView.findViewById(R.id.to_time);
            amn=itemView.findViewById(R.id.redeemed_amount);
            coupen_card=itemView.findViewById(R.id.coupen_card);
        }
    }
}