package com.WeShowedUp.radharanipoojagallery.ui.coupon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.WeShowedUp.radharanipoojagallery.Controller.CouponActivity;
import com.WeShowedUp.radharanipoojagallery.R;
import com.WeShowedUp.radharanipoojagallery.Response.CouponResponse.Datum;

import java.util.List;

public class CouponViewModel extends RecyclerView.Adapter<CouponViewModel.ViewHolder> {
   private LayoutInflater inflater;
   private Context context;
   private List<Datum> Mycoupon;

    public CouponViewModel(Context context, List<Datum> coupon) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i)
    {
         holder.cno.setText(String.valueOf(Mycoupon.get(i).getId()));
         holder.price.setText(String.valueOf(Mycoupon.get(i).getAmount()));
         holder.from.setText(String.valueOf(Mycoupon.get(i).getStart()));
         holder.to.setText(String.valueOf(Mycoupon.get(i).getEnd()));
        if (Mycoupon.get(i).getStatus()==0) {
            holder.reedem.setText("Reedem");
            holder.imageView.setColorFilter(Color.BLACK);
        } else {
            holder.reedem.setText("Reedemed");
        }
         holder.coupen_card.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                 Intent intent=new Intent(context, CouponActivity.class);
                 intent.putExtra("status", String.valueOf(Mycoupon.get(i).getStatus()));
                 intent.putExtra("coupen_id",Mycoupon.get(i).getId());
                 intent.putExtra("start",Mycoupon.get(i).getStart());
                 intent.putExtra("end",Mycoupon.get(i).getEnd());
                 intent.putExtra("amount", String.valueOf(Mycoupon.get(i).getAmount()));
                 intent.putExtra("post_id",Mycoupon.get(i).getPostId());
                 context.startActivity(intent);
             }
         });
    }

    @Override
    public int getItemCount() {
        return Mycoupon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView cno,to,from,price,reedem;
        private LinearLayout coupen_card;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.coupon_line);
            cno=itemView.findViewById(R.id.redeemed_coupon_no);
            from=itemView.findViewById(R.id.from);
            to=itemView.findViewById(R.id.to);
            reedem=itemView.findViewById(R.id.reedemed);
            price=itemView.findViewById(R.id.amount);
            coupen_card=itemView.findViewById(R.id.coupen_card);
        }
    }
}