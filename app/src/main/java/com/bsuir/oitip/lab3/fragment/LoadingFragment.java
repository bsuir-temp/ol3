package com.bsuir.oitip.lab3.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bsuir.oitip.lab3.R;
import com.bsuir.oitip.lab3.firebase.FirebaseAuthRepo;
import com.bsuir.oitip.lab3.model.Question;
import com.bsuir.oitip.lab3.model.Wrapper;
import com.bsuir.oitip.lab3.retrofit.RetroPoints;
import com.bsuir.oitip.lab3.retrofit.RetrofitCore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingFragment extends FragmentEvent {

    public static final String TAG = "LoadingFragment";
    private FirebaseAuthRepo authRepo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authRepo = new FirebaseAuthRepo();
        Fragment fragment = null;
        if(!authRepo.isSignedIn())
            fragment = new LoginFragment();
        else
            fragment = new MainMenuFragment();
        getFragmentManager().beginTransaction().replace(R.id.content,fragment,TAG).commit();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loading, null);
    }

}
