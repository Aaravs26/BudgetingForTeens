package com.example.budgetingforteens;

import androidx.room.Entity;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class User {
    private String activity;
    private String expenditure;
    private String date;
    public User() {}

    public User(String activity, String expenditure, String date) {
        this.activity = activity;
        this.expenditure = expenditure;
        this.date = date;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
