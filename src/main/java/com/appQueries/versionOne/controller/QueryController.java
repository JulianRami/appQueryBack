package com.appQueries.versionOne.controller;

import com.appQueries.versionOne.dto.QueryDto;
import com.appQueries.versionOne.model.Query;
import com.appQueries.versionOne.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/queries")
public class QueryController {

    @Autowired
    private QueryService queryService;

    /**
     * Retrieves all queries.
     *
     * @return ResponseEntity containing a List of QueryDto objects.
     */
    @GetMapping
    public List<QueryDto> getAllQueries() {
        return queryService.getAllQueries();
    }

    /**
     * Retrieves a query by ID.
     *
     * @param id Query ID.
     * @return ResponseEntity containing the queried Query object.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Query> getQueryById(@PathVariable Long id) {
        Query query = queryService.getQueryById(id);
        return ResponseEntity.ok(query);
    }

    /**
     * Retrieves all queries by username.
     *
     * @param username Username.
     * @return ResponseEntity containing a List of QueryDto objects.
     */
    @GetMapping("/by-username/{username}")
    public ResponseEntity<List<QueryDto>> getQueriesByNameUser(@PathVariable String username) {
        List<QueryDto> queriesList = queryService.getQueryByName(username);
        return new ResponseEntity<>(queriesList, HttpStatus.OK);
    }

    /**
     * Creates a new query.
     *
     * @param query QueryDto object containing query details.
     * @return ResponseEntity containing the created Query object.
     */
    @PostMapping
    public ResponseEntity<Query> createQuery(@RequestBody QueryDto query) {
        if (query == null) {
            return ResponseEntity.badRequest().build();
        }
        Query createdQuery = queryService.createQuery(query);
        return ResponseEntity.ok(createdQuery);
    }

    /**
     * Updates a query by ID.
     *
     * @param id    Query ID.
     * @param query Query object containing updated details.
     * @return ResponseEntity containing the updated Query object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Query> updateQuery(@PathVariable Long id, @RequestBody Query query) {
        Query updatedQuery = queryService.updateQuery(id, query);
        return ResponseEntity.ok(updatedQuery);
    }

    /**
     * Deletes a query by ID.
     *
     * @param id Query ID.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuery(@PathVariable Long id) {
        queryService.deleteQuery(id);
        return ResponseEntity.noContent().build();
    }
}
