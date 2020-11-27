package com.bsuir.oitip.lab3.firebase;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bsuir.oitip.lab3.fragment.FragmentEvent;
import com.bsuir.oitip.lab3.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseDataScores {

    public final static String TAG = "FirebaseAuthRepo";

    private FirebaseDatabase database = null;
    private DatabaseReference ref;
    public final static String users = "users";

    public FirebaseDataScores()
    {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
    }

    public void addUser(User user)
    {
        Map<String, Object> userData = new HashMap<String, Object>();
        userData.put("loses", user.loses);
        userData.put("wins", user.wins);
        ref = ref.child(users).child(user.email);
        ref.setValue(userData);
    }

    public void readTop(final ArrayList<User> userArrayList, FragmentEvent fragmentEvent)
    {
        ref.child(users).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (Map.Entry<String, Object> entry : ((Map<String,Object>) snapshot.getValue()).entrySet()){
                    Map singleUser = (Map) entry.getValue();
                    User user = new User();
                    user.email = entry.getKey();
                    user.loses = (long) singleUser.get("loses");
                    user.wins = (long) singleUser.get("wins");
                    userArrayList.add(user);
                }
            }
            @Override public void onCancelled(@NonNull DatabaseError error) { fragmentEvent.onError(error.getMessage()); }
        });


    }

}
