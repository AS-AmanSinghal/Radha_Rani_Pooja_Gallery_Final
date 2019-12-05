package com.WeShowedUp.radharanipoojagallery.ui.myphoto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.WeShowedUp.radharanipoojagallery.Controller.PhotoActivity;
import com.WeShowedUp.radharanipoojagallery.Lists.MyPhoto;
import com.WeShowedUp.radharanipoojagallery.R;

import java.util.List;

public class MyPhotoViewModel extends RecyclerView.Adapter<MyPhotoViewModel.ViewHolder>
{

    private Context context;
    private LayoutInflater inflater;
    private List<MyPhoto> Myphotos;
    public MyPhotoViewModel(Context context, List<MyPhoto> myphotos) {
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
        viewHolder.name.setText("Krishna");
        viewHolder.price.setText("280");
        viewHolder.verified.setText("Verified");
        viewHolder.date.setText("2019-11-20");
        viewHolder.like.setText("4");
        viewHolder.share.setText("10");
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                context.startActivity(new Intent(context, PhotoActivity.class));
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
        private LinearLayout card;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.name_myphoto);
            image=itemView.findViewById(R.id.image_myphoto);
            price=itemView.findViewById(R.id.price_myphoto);
            date=itemView.findViewById(R.id.date_myphoto);
            like=itemView.findViewById(R.id.likes_myphoto);
            share=itemView.findViewById(R.id.share_myphoto);
            verified=itemView.findViewById(R.id.verified_myphoto);
            card=itemView.findViewById(R.id.myphoto_card);
        }
    }
}