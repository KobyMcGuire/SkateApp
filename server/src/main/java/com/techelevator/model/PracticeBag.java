package com.techelevator.model;

public class PracticeBag {

    private int id;
    private String date;

    public PracticeBag() {}

    public PracticeBag(int id, String date) {
        this.id =id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
