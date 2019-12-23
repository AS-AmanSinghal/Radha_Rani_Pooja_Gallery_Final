package com.WeShowedUp.radharanipoojagallery.ui.feed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.WeShowedUp.radharanipoojagallery.Controller.PhotoActivity;
import com.WeShowedUp.radharanipoojagallery.R;
import com.WeShowedUp.radharanipoojagallery.Response.MyPhotoResponse.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FeedViewModel extends RecyclerView.Adapter<FeedViewModel.ViewHolder>
{

    private Context context;
    private LayoutInflater inflater;
    private List<Datum> Myphotos;
//    private List<MyPhoto> Myphotos;
    public FeedViewModel(Context context, List<Datum> myphotos) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        Myphotos = myphotos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.myphoto_card,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i)
    {
        Picasso.get().load(Myphotos.get(i).getUrl()).into(viewHolder.image);
        viewHolder.name.setText(Myphotos.get(i).getMessage());
        viewHolder.price.setText(String.valueOf(Myphotos.get(i).getPrice()));
        viewHolder.date.setText(Myphotos.get(i).getCreationTime());
        viewHolder.like.setText(String.valueOf(Myphotos.get(i).getLikes()));
        viewHolder.share.setText(String.valueOf(Myphotos.get(i).getShares()));
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(context, PhotoActivity.class);
                intent.putExtra("post_id",String.valueOf(Myphotos.get(i).getId()));
                intent.putExtra("image",Myphotos.get(i).getUrl());
                intent.putExtra("message",Myphotos.get(i).getMessage());
                intent.putExtra("price",String.valueOf(Myphotos.get(i).getPrice()));
                intent.putExtra("likes",String.valueOf(Myphotos.get(i).getLikes()));
                intent.putExtra("shares",String.valueOf(Myphotos.get(i).getShares()));
                intent.putExtra("date",Myphotos.get(i).getCreationTime());
                intent.putExtra("status",String.valueOf(Myphotos.get(i).getStatus()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return Myphotos.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private TextView name,price,date,verified,like,share;
        private CardView card;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.name_myphoto);
            image=itemView.findViewById(R.id.image_myphoto);
            price=itemView.findViewById(R.id.price_myphoto);
            date=itemView.findViewById(R.id.date_myphoto);
            like=itemView.findViewById(R.id.likes_myphoto);
            share=itemView.findViewById(R.id.share_myphoto);
            //verified=itemView.findViewById(R.id.verified_myphoto);
            card=itemView.findViewById(R.id.myphoto_card);
        }
    }
}