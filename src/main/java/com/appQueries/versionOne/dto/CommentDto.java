package com.appQueries.versionOne.dto;
/**
 * CommentDto for transfer dates with front.
 */
public class CommentDto {

    private String commentText;
    private String user;
    private long queryId;

    public CommentDto() {
    }

    public CommentDto(String commentText, String user, long queryId) {
        this.commentText = commentText;
        this.user = user;
        this.queryId = queryId;
    }

    /**
     * Gets the comment text.
     *
     * @return The comment text.
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * Sets the comment text.
     *
     * @param commentText The comment text to set.
     */
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    /**
     * Gets the user associated with the comment.
     *
     * @return The user.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user associated with the comment.
     *
     * @param user The user to set.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the ID of the query associated with the comment.
     *
     * @return The query ID.
     */
    public long getQueryId() {
        return queryId;
    }

    /**
     * Sets the ID of the query associated with the comment.
     *
     * @param queryId The query ID to set.
     */
    public void setQueryId(long queryId) {
        this.queryId = queryId;
    }
}
