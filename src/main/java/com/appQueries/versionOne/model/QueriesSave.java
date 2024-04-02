package com.appQueries.versionOne.model;

import jakarta.persistence.*;


@Entity
@Table(name = "queries_save")
public class QueriesSave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "query_id")
    private Query query;

    public QueriesSave() {
    }

    public QueriesSave(User user, Query query) {
        this.user = user;
        this.query = query;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long saveId) {
        this.id = saveId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
