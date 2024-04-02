package com.appQueries.versionOne.service;
import com.appQueries.versionOne.exception.UsernameAlreadyExistsException;
import com.appQueries.versionOne.model.User;
import com.appQueries.versionOne.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all users.
     *
     * @return List of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user.
     * @return The user with the given ID.
     * @throws EntityNotFoundException if the user with the given ID is not found.
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    /**
     * Creates a new user.
     *
     * @param user The user to be created.
     * @return The created user.
     * @throws UsernameAlreadyExistsException if the username is already in use.
     */
    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Exception handling: Username already exists
            throw new UsernameAlreadyExistsException("Username is already in use. Please choose another one.");
        }
    }

    /**
     * Updates an existing user.
     *
     * @param id   The ID of the user to be updated.
     * @param user The updated user information.
     * @return The updated user.
     * @throws EntityNotFoundException if the user with the given ID is not found.
     */
    public User updateUser(Long id, User user) {
        // Verifies if the user exists before updating
        getUserById(id);

        user.setId(id);
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to be deleted.
     * @throws EntityNotFoundException if the user with the given ID is not found.
     */
    public void deleteUser(Long id) {
        // Verifies if the user exists before deleting
        getUserById(id);

        userRepository.deleteById(id);
    }
}
