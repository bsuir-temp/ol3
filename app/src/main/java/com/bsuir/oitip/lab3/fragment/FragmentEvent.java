package com.bsuir.oitip.lab3.fragment;

import android.app.AlertDialog;

import androidx.fragment.app.Fragment;

public abstract class FragmentEvent extends Fragment{

    public void onError(String why)
    {
        new AlertDialog.Builder(getContext()).setTitle("Error").setMessage(why).create().show();
    }

}
