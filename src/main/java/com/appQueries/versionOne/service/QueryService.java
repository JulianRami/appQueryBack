package com.appQueries.versionOne.service;

import com.appQueries.versionOne.dto.QueryDto;
import com.appQueries.versionOne.model.Query;
import com.appQueries.versionOne.model.User;
import com.appQueries.versionOne.repository.QueryRepository;
import com.appQueries.versionOne.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QueryService {

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all queries along with user information.
     *
     * @return List of QueryDto containing query and user information.
     */
    public List<QueryDto> getAllQueries() {
        List<Query> queries = queryRepository.findAll();
        return queries.stream()
                .map(query -> new QueryDto(
                        query.getId(),
                        query.getQueryName(),
                        query.getDescription(),
                        query.getDate(),
                        query.getSearchTerm(),
                        query.getCountry(),
                        query.getRecordCount(),
                        query.getUserId().getUsername()
                )).collect(Collectors.toList());
    }

    /**
     * Retrieves a query by its ID.
     *
     * @param id The ID of the query.
     * @return The query with the given ID.
     * @throws EntityNotFoundException if the query with the given ID is not found.
     */
    public Query getQueryById(Long id) {
        return queryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Query not found with id: " + id));
    }

    /**
     * Creates a new query.
     *
     * @param queryDto The DTO containing query information.
     * @return The created query.
     * @throws EntityNotFoundException if the associated user is not found.
     */
    public Query createQuery(QueryDto queryDto) throws EntityNotFoundException {
        Query query = new Query();
        Optional<User> userOptional = userRepository.findByUsername(queryDto.getUser());
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User not found with username: " + queryDto.getUser());
        }
        User user = userOptional.get();
        query.setUserId(user);
        BeanUtils.copyProperties(queryDto, query);
        return queryRepository.save(query);
    }

    /**
     * Updates an existing query.
     *
     * @param id    The ID of the query to be updated.
     * @param query The updated query information.
     * @return The updated query.
     * @throws EntityNotFoundException if the query with the given ID is not found.
     */
    public Query updateQuery(Long id, Query query) {
        // Verifies if the query exists before updating
        getQueryById(id);

        query.setId(id);
        return queryRepository.save(query);
    }

    /**
     * Deletes a query by its ID.
     *
     * @param id The ID of the query to be deleted.
     * @throws EntityNotFoundException if the query with the given ID is not found.
     */
    public void deleteQuery(Long id) {
        // Verifies if the query exists before deleting
        getQueryById(id);

        queryRepository.deleteById(id);
    }

    /**
     * Retrieves all queries associated with a user.
     *
     * @param username The username of the user.
     * @return List of QueryDto containing query and user information.
     */
    public List<QueryDto> getQueryByName(String username) {
        List<Query> queries = queryRepository.findByUserUsername(username);
        return queries.stream()
                .map(query -> new QueryDto(
                        query.getId(),
                        query.getQueryName(),
                        query.getDescription(),
                        query.getDate(),
                        query.getSearchTerm(),
                        query.getCountry(),
                        query.getRecordCount(),
                        query.getUserId().getUsername()
                )).collect(Collectors.toList());
    }
}

