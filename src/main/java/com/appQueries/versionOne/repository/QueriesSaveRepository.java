package com.appQueries.versionOne.repository;

import com.appQueries.versionOne.model.QueriesSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueriesSaveRepository extends JpaRepository<QueriesSave, Long> {
    /**
     * Retrieves a list of queries saved by a user based on the user's ID.
     *
     * @param userId The ID of the user.
     * @return A list of queries saved by the user with the given ID.
     */
    List<QueriesSave> findByUserId(Long userId);

    /**
     * Checks if a user has saved a specific query.
     *
     * @param userId  The ID of the user.
     * @param queryId The ID of the query.
     * @return True if the user has saved the query, false otherwise.
     */
    boolean existsByUserIdAndQueryId(Long userId, Long queryId);

    /**
     * Retrieves a list of queries saved by a user based on the username.
     *
     * @param username The username of the user.
     * @return A list of queries saved by the user with the given username.
     */
    @Query("SELECT qs FROM QueriesSave qs JOIN qs.user u WHERE u.username = :username")
    List<QueriesSave> findByUserUsername(@Param("username") String username);

}
