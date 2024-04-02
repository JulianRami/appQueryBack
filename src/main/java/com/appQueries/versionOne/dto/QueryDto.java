package com.appQueries.versionOne.dto;

import java.util.Date;

public class QueryDto {

    private Long id;
    private String queryName;
    private String description;
    private Date date;
    private String searchTerm;
    private String country;
    private int recordCount;
    private String user;

    public QueryDto() {
        // Empty constructor required by JPA
    }

    /**
     * Constructor for creating a QueryDto with specified attributes.
     *
     * @param id          The ID of the query.
     * @param queryName   The name of the query.
     * @param description The description of the query.
     * @param date        The date of the query.
     * @param searchTerm  The search term of the query.
     * @param country     The country of the query.
     * @param recordCount The record count of the query.
     * @param user        The user associated with the query.
     */
    public QueryDto(Long id, String queryName, String description, Date date, String searchTerm, String country, int recordCount, String user) {
        this.id = id;
        this.queryName = queryName;
        this.description = description;
        this.date = date;
        this.searchTerm = searchTerm;
        this.country = country;
        this.recordCount = recordCount;
        this.user = user;
    }

    /**
     * Gets the ID of the query.
     *
     * @return The query ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the query.
     *
     * @param id The query ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the query.
     *
     * @return The query name.
     */
    public String getQueryName() {
        return queryName;
    }

    /**
     * Sets the name of the query.
     *
     * @param queryName The query name to set.
     */
    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    /**
     * Gets the description of the query.
     *
     * @return The query description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the query.
     *
     * @param description The query description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the date of the query.
     *
     * @return The query date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the query.
     *
     * @param date The query date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the search term of the query.
     *
     * @return The query search term.
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     * Sets the search term of the query.
     *
     * @param searchTerm The query search term to set.
     */
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Gets the country of the query.
     *
     * @return The query country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the query.
     *
     * @param country The query country to set.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the record count of the query.
     *
     * @return The query record count.
     */
    public int getRecordCount() {
        return recordCount;
    }

    /**
     * Sets the record count of the query.
     *
     * @param recordCount The query record count to set.
     */
    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    /**
     * Gets the user associated with the query.
     *
     * @return The user.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user associated with the query.
     *
     * @param user The user to set.
     */
    public void setUser(String user) {
        this.user = user;
    }
}