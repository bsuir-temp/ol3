package com.bsuir.oitip.lab3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bsuir.oitip.lab3.R;
import com.bsuir.oitip.lab3.firebase.FirebaseAuthRepo;

public class ResultsFragment extends FragmentEvent {

    public static final String TAG = "ResultsFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_results, null);
        ((TextView)root.findViewById(R.id.correct)).setText(String.valueOf(getArguments().getInt("correct")));
        ((TextView)root.findViewById(R.id.incorrect)).setText(String.valueOf(getArguments().getInt("incorrect")));
        final FirebaseAuthRepo repo = new FirebaseAuthRepo();
        root.findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.content,new MainMenuFragment()).commit();
            }
        });
        return root;
    }

}
