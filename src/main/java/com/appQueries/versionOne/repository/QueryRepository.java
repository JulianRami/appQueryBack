package com.appQueries.versionOne.repository;

import com.appQueries.versionOne.model.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {
    /**
     * Retrieves a list of queries created by a user based on the username.
     *
     * @param username The username of the user.
     * @return A list of queries created by the user with the given username.
     */
    @org.springframework.data.jpa.repository.Query("SELECT qs FROM Query qs JOIN qs.userId u WHERE u.username = :username")
    List<Query> findByUserUsername(@Param("username") String username);

}


