package com.project.seminar.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.seminar.Adapter.HerosAdapter;
import com.project.seminar.Model.GetHeros;
import com.project.seminar.Model.Heros;
import com.project.seminar.R;
import com.project.seminar.RestServer.ApiClient;
import com.project.seminar.RestServer.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeminarFragment extends Fragment {

    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SeminarFragment seminarFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seminar, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_heros);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        seminarFragment = this;
        refresh();
    }

    public void refresh() {
        Call<GetHeros> HerosCall = mApiInterface.getHeros();
        HerosCall.enqueue(new Callback<GetHeros>() {
            @Override
            public void onResponse(Call<GetHeros> call, Response<GetHeros>
                    response) {
                List<Heros> HerosList = response.body().getListDataHeros();
                Log.d("Retrofit Get", "Jumlah data Heros: " +
                        String.valueOf(HerosList.size()));
                mAdapter = new HerosAdapter(HerosList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetHeros> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}