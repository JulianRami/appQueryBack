package com.appQueries.versionOne.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "queries")
public class Query {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String queryName;

    private String description;

    private Date date;

    private String searchTerm;

    private String country;

    private int recordCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    public Query() {
    }

    public Query(String queryName, String description, Date date, String searchTerm, String country, int recordCount, User userId) {
        this.queryName = queryName;
        this.description = description;
        this.date = date;
        this.searchTerm = searchTerm;
        this.country = country;
        this.recordCount = recordCount;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long queryId) {
        this.id = queryId;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

}