package com.appQueries.versionOne.controller;

import com.appQueries.versionOne.dto.QueriesSaveDto;
import com.appQueries.versionOne.dto.QueryDto;
import com.appQueries.versionOne.model.QueriesSave;
import com.appQueries.versionOne.service.QueriesSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/queries-save")
public class QueriesSaveController {

    @Autowired
    private QueriesSaveService queriesSaveService;

    /**
     * Retrieves all queries saved by a user based on user ID.
     *
     * @param userId User ID.
     * @return ResponseEntity containing a List of QueriesSave objects.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<QueriesSave>> getQueriesByUserId(@PathVariable Long userId) {
        List<QueriesSave> queriesSaveList = queriesSaveService.getQueriesByUserId(userId);
        return new ResponseEntity<>(queriesSaveList, HttpStatus.OK);
    }

    /**
     * Retrieves all queries saved by a user based on username.
     *
     * @param username Username.
     * @return ResponseEntity containing a List of QueryDto objects.
     */
    @GetMapping("/by-username/{username}")
    public ResponseEntity<List<QueryDto>> getQueriesByNameUser(@PathVariable String username) {
        List<QueryDto> queriesSaveList = queriesSaveService.getQueriesByNameUser(username);
        return new ResponseEntity<>(queriesSaveList, HttpStatus.OK);
    }

    /**
     * Saves a new query for a user.
     *
     * @param request QueriesSaveDto object containing user and query ID.
     * @return ResponseEntity containing the saved QueriesSave object.
     * @throws ChangeSetPersister.NotFoundException if the associated query ID is not found.
     */
    @PostMapping("/save-query")
    public ResponseEntity<QueriesSave> saveQuery(@RequestBody QueriesSaveDto request) throws ChangeSetPersister.NotFoundException {
        QueriesSave savedQuery = queriesSaveService.saveQuery(request.getUser(), request.getIdQuery());
        return new ResponseEntity<>(savedQuery, HttpStatus.CREATED);
    }

    /**
     * Deletes a saved query for a user based on save ID.
     *
     * @param saveId Save ID.
     * @return ResponseEntity with a success message.
     */
    @DeleteMapping("/delete-query/{saveId}")
    public ResponseEntity<String> deleteSavedQuery(@PathVariable Long saveId) {
        queriesSaveService.deleteSavedQuery(saveId);
        return new ResponseEntity<>("Saved query successfully deleted", HttpStatus.OK);
    }
}

