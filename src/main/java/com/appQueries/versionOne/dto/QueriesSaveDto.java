package com.appQueries.versionOne.dto;
/**
 * QueriesSaveDto for transfer dates with front.
 */
public class QueriesSaveDto {

    private String user;
    private Long idQuery;

    public QueriesSaveDto() {

    }

    public QueriesSaveDto(String user, Long idQuery) {
        this.user = user;
        this.idQuery = idQuery;
    }

    /**
     * Gets the user associated with the saved query.
     *
     * @return The user.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user associated with the saved query.
     *
     * @param user The user to set.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the ID of the query to be saved.
     *
     * @return The query ID.
     */
    public Long getIdQuery() {
        return idQuery;
    }

    /**
     * Sets the ID of the query to be saved.
     *
     * @param idQuery The query ID to set.
     */
    public void setIdQuery(Long idQuery) {
        this.idQuery = idQuery;
    }
}
