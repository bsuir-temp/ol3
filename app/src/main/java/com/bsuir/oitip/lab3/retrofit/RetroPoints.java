package com.bsuir.oitip.lab3.retrofit;

import com.bsuir.oitip.lab3.model.Question;
import com.bsuir.oitip.lab3.model.Wrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroPoints {

    @GET("/api.php?amount=10&type=multiple&encode=url3986")
    Call<Wrapper> getQuestions();

}