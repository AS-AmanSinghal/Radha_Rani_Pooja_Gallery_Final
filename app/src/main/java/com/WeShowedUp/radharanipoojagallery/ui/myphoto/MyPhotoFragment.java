package com.WeShowedUp.radharanipoojagallery.ui.myphoto;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.WeShowedUp.radharanipoojagallery.Lists.MyPhoto;
import com.WeShowedUp.radharanipoojagallery.R;

import java.util.ArrayList;
import java.util.List;


public class MyPhotoFragment extends Fragment{

    private MyPhotoViewModel myPhotoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_myphoto, container, false);
        return root;
    }

    private RecyclerView recyclerView;
    private MyPhotoViewModel adapter;
    private List<MyPhoto> myPhotolist=new ArrayList<>();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycler_myphoto);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        Context context;
        getMyPhoto();
        adapter=new MyPhotoViewModel(getContext(),myPhotolist);
        recyclerView.setAdapter(adapter);
    }

    private void getMyPhoto()
    {
        MyPhoto photo=new MyPhoto();
        myPhotolist.add(photo);
        myPhotolist.add(photo);
        myPhotolist.add(photo);
        myPhotolist.add(photo);
        myPhotolist.add(photo);
        adapter=new MyPhotoViewModel(getContext(),myPhotolist);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}