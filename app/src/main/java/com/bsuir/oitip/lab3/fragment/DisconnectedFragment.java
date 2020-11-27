package com.bsuir.oitip.lab3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bsuir.oitip.lab3.R;
import com.bsuir.oitip.lab3.model.Wrapper;
import com.bsuir.oitip.lab3.retrofit.RetroPoints;
import com.bsuir.oitip.lab3.retrofit.RetrofitCore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisconnectedFragment extends FragmentEvent {

    public static final String TAG = "DisconnectedFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_disconnected, null);
    }

}
