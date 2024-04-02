package com.appQueries.versionOne.repository;

import com.appQueries.versionOne.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Retrieves a user based on the username.
     *
     * @param nameUser The username of the user.
     * @return An optional containing the user with the given username, or empty if not found.
     */
    Optional<User> findByUsername(String nameUser);

}
