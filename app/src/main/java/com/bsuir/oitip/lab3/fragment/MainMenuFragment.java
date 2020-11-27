package com.bsuir.oitip.lab3.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

public class MainMenuFragment extends FragmentEvent {

    public static final String TAG = "MainMenuFragment";
    FirebaseAuthRepo authRepo = new FirebaseAuthRepo();
    private View items[] = new View[4];
    private View.OnClickListener startQ = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment fragment = new QuestionFragment();
            getFragmentManager().beginTransaction().replace(R.id.content,fragment).commit();
        }
    };
    private View.OnClickListener logout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            authRepo.logout();
            Fragment fragment = new LoginFragment();
            getFragmentManager().beginTransaction().replace(R.id.content,fragment).commit();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, null);
        items[0] = root.findViewById(R.id.welcome);
        ((TextView)items[0]).setText("Welcome, "+authRepo.mAuth.getCurrentUser().getEmail());
        items[1] = root.findViewById(R.id.btn_start);
        items[1].setOnClickListener(startQ);
        items[3] = root.findViewById(R.id.logout_button);
        items[3].setOnClickListener(logout);
        return root;
    }

}
