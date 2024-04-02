package com.appQueries.versionOne.repository;

import com.appQueries.versionOne.model.Comment;
import com.appQueries.versionOne.model.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * Retrieves a list of comments based on the associated query.
     *
     * @param query The associated query.
     * @return A list of comments associated with the given query.
     */
    List<Comment> findByQueryId(Query query);
}

