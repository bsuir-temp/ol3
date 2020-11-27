package com.bsuir.oitip.lab3.model;

public class User {

    public String email;
    public String password;
    public long wins = 0;
    public long loses = 0;


    @Override
    public String toString()
    {
        return "email ["+email+"] wins ["+wins+"] loses ["+loses+"]";
    }

}
