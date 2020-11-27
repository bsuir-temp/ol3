package com.bsuir.oitip.lab3.firebase;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bsuir.oitip.lab3.MainActivity;
import com.bsuir.oitip.lab3.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthRepo {

    public final static String TAG = "FirebaseAuthRepo";

    public FirebaseAuth mAuth = null;

    public FirebaseAuthRepo()
    {
        mAuth = FirebaseAuth.getInstance();
    }

    public boolean isSignedIn()
    {
        return mAuth.getCurrentUser()!=null;
    }

    public void registerUser(Activity activity, User user,OnCompleteListener<AuthResult> task)
    {
        mAuth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener(activity,task);
    }

    public void signIn(Activity activity, User user,OnCompleteListener<AuthResult> task)throws Throwable
    {
        mAuth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener(activity,task);
    }

    public void logout()
    {
        mAuth.signOut();
    }



}
