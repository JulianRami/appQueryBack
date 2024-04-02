package com.appQueries.versionOne.model;

public class Search {
    private String country;
    private String date;
    private String term;
    private int limit;

    public Search() {
    }

    public Search(String country, String date, String term, int limit) {
        this.country = country;
        this.date = date;
        this.term = term;
        this.limit = limit;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

