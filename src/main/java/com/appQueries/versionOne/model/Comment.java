package com.appQueries.versionOne.model;


import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commentText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "query_id")
    private Query queryId;

    public Comment() {
    }

    public Comment(String commentText, User userId, Query queryId) {
        this.commentText = commentText;
        this.userId = userId;
        this.queryId = queryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User user) {
        this.userId = user;
    }

    public Query getQueryId() {
        return queryId;
    }

    public void setQueryId(Query queryId) {
        this.queryId = queryId;
    }
}

