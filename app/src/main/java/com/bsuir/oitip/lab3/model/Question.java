package com.bsuir.oitip.lab3.model;


import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("category")
    public String category;

    @SerializedName("difficulty")
    public String difficulty;

    @SerializedName("question")
    public String question;

    @SerializedName("correct_answer")
    public String correct_answer;

    @SerializedName("incorrect_answers")
    public String[] incorrect_answers;

}
