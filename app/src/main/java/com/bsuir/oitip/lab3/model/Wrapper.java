package com.bsuir.oitip.lab3.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Wrapper {

    @SerializedName("results")
    public List<Question> results;

}
