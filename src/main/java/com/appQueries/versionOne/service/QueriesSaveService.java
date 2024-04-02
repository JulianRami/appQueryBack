package com.appQueries.versionOne.service;

import com.appQueries.versionOne.dto.QueryDto;
import com.appQueries.versionOne.model.QueriesSave;
import com.appQueries.versionOne.model.Query;
import com.appQueries.versionOne.model.User;
import com.appQueries.versionOne.repository.QueriesSaveRepository;
import com.appQueries.versionOne.repository.QueryRepository;
import com.appQueries.versionOne.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * The `QueriesSaveService` class provides methods for managing saved queries in the application.
 */
@Service
public class QueriesSaveService {

    private final QueriesSaveRepository queriesSaveRepository;
    private final UserRepository userRepository;
    private final QueryRepository queryRepository;

    /**
     * Constructs a new instance of `QueriesSaveService`.
     *
     * @param queriesSaveRepository The repository for managing `QueriesSave` entities.
     * @param userRepository       The repository for managing `User` entities.
     * @param queryRepository      The repository for managing `Query` entities.
     */
    @Autowired
    public QueriesSaveService(QueriesSaveRepository queriesSaveRepository,
                              UserRepository userRepository,
                              QueryRepository queryRepository) {
        this.queriesSaveRepository = queriesSaveRepository;
        this.userRepository = userRepository;
        this.queryRepository = queryRepository;
    }

    /**
     * Saves a query for a user.
     *
     * @param nameUser The username of the user.
     * @param queryId  The ID of the query to be saved.
     * @return The saved `QueriesSave` entity.
     * @throws ChangeSetPersister.NotFoundException If the user or query is not found.
     */
    public QueriesSave saveQuery(String nameUser, Long queryId) throws ChangeSetPersister.NotFoundException {
        // Find the user by username
        Optional<User> userOptional = userRepository.findByUsername(nameUser);
        if (userOptional.isEmpty()) {
            // Handle the case where the user does not exist
            throw new ChangeSetPersister.NotFoundException();
        }
        User user = userOptional.get();

        // Find the query by ID
        Optional<Query> queryOptional = queryRepository.findById(queryId);
        if (queryOptional.isEmpty()) {
            // Handle the case where the query does not exist
            throw new ChangeSetPersister.NotFoundException();
        }
        Query query = queryOptional.get();

        // Create and save the QueriesSave object
        QueriesSave queriesSave = new QueriesSave();
        queriesSave.setUser(user);
        queriesSave.setQuery(query);
        return queriesSaveRepository.save(queriesSave);
    }

    /**
     * Retrieves all queries saved by a user.
     *
     * @param userId The ID of the user.
     * @return The list of saved queries for the user.
     */
    public List<QueriesSave> getQueriesByUserId(Long userId) {
        return queriesSaveRepository.findByUserId(userId);
    }

    /**
     * Deletes a saved query for a user.
     *
     * @param saveId The ID of the saved query to be deleted.
     */
    public void deleteSavedQuery(Long saveId) {
        queriesSaveRepository.deleteById(saveId);
    }

    /**
     * Retrieves saved queries as `QueryDto` objects based on the user's username.
     *
     * @param userId The username of the user.
     * @return The list of `QueryDto` objects representing the saved queries.
     */
    public List<QueryDto> getQueriesByNameUser(String userId) {
        List<QueriesSave> queries = queriesSaveRepository.findByUserUsername(userId);
        return queries.stream()
                .map(query -> new QueryDto(
                        query.getQuery().getId(),
                        query.getQuery().getQueryName(),
                        query.getQuery().getDescription(),
                        query.getQuery().getDate(),
                        query.getQuery().getSearchTerm(),
                        query.getQuery().getCountry(),
                        query.getQuery().getRecordCount(),
                        query.getQuery().getUserId().getUsername()
                )).collect(Collectors.toList());
    }
}


