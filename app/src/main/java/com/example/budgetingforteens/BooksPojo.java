package com.example.budgetingforteens;

public class BooksPojo {
    private String uid;
    private String activity;
    private String expenditure;
    private String date;
    public BooksPojo() {}

    public BooksPojo(String uid,String activity, String expenditure, String date) {
        this.uid = uid;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
