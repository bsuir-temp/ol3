package com.bsuir.oitip.lab3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bsuir.oitip.lab3.fragment.FragmentEvent;
import com.bsuir.oitip.lab3.fragment.LoadingFragment;
import com.bsuir.oitip.lab3.model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(getApplicationContext());
        getSupportFragmentManager().beginTransaction().replace(R.id.content, new LoadingFragment()).commit();
/*

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        User user = new User();
        user.email="lelelel";
        user.loses=0;
        user.wins=0;
        addUser(user,ref);
        ref.child(users).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override public void onDataChange(@NonNull DataSnapshot snapshot) { readTop((Map<String,Object>) snapshot.getValue()); }
            @Override public void onCancelled(@NonNull DatabaseError error) { }
        });
 */
    }



}