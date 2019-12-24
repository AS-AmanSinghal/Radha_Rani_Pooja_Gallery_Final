package com.WeShowedUp.radharanipoojagallery.ui.myphoto;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.WeShowedUp.radharanipoojagallery.General.RetrofitClass;
import com.WeShowedUp.radharanipoojagallery.R;
import com.WeShowedUp.radharanipoojagallery.Response.MyPhotoResponse.Datum;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyPhotoFragment extends Fragment
{
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_myphoto, container, false);
        return root;
    }

    private RecyclerView recyclerView;
    private MyPhotoViewModel adapter;
    private List<Datum> myPhotolist=new ArrayList<>();
//    private List<MyPhoto> myPhotolist=new ArrayList<>();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycler_myphoto);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        new MyPhoto().execute();
        adapter=new MyPhotoViewModel(getContext(),myPhotolist);
        recyclerView.setAdapter(adapter);
    }

    public class MyPhoto extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {
            RetrofitClass retrofitClass=new RetrofitClass();
            Call<JsonObject> call=retrofitClass.retrofit().post(getActivity().getIntent().getStringExtra("phone"));
            call.enqueue(new Callback<JsonObject>() {
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
                                datum.setUrl(jsonObject2.optString("url"));
                                datum.setMessage(jsonObject2.optString("message"));
                                datum.setCreationTime(jsonObject2.optString("creation_time"));
                                datum.setPrice(jsonObject2.optLong("price"));
                                datum.setShares(jsonObject2.optLong("shares"));
                                datum.setLikes(jsonObject2.optLong("likes"));
                                datum.setStatus(jsonObject2.optLong("status"));
                                datum.setId(jsonObject2.optString("id"));
                                myPhotolist.add(datum);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter=new MyPhotoViewModel(getContext(),myPhotolist);
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