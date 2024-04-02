package com.appQueries.versionOne.model;

import java.util.Date;

public class QueryData {

    private String searchTerm;
    private String country;
    private Date date;
    private int ranking;
    private int score;

    public QueryData() {
    }

    public QueryData(Date date, String searchTerm, int ranking, int score, String country) {
        this.date = date;
        this.searchTerm = searchTerm;
        this.ranking = ranking;
        this.score = score;
        this.country = country;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchQuery) {
        this.searchTerm = searchQuery;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}

