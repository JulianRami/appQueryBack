package com.appQueries.versionOne.Controller;

/**
 * This class contains unit tests for the QueriesSaveController class.
 */
import com.appQueries.versionOne.dto.QueriesSaveDto;
import com.appQueries.versionOne.dto.QueryDto;
import com.appQueries.versionOne.model.QueriesSave;
import com.appQueries.versionOne.controller.QueriesSaveController;
import com.appQueries.versionOne.model.Query;
import com.appQueries.versionOne.model.User;
import com.appQueries.versionOne.service.QueriesSaveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
class QueriesSaveControllerTest {

    @Mock
    private QueriesSaveService queriesSaveService;

    @InjectMocks
    private QueriesSaveController queriesSaveController;

    @BeforeEach
    void setUp() {
        // Additional configurations can be performed here if necessary
    }

    /**
     * Unit test for the getQueriesByUserId method of QueriesSaveController.
     */
    @Test
    void getQueriesByUserId() {
        // Arrange
        Long userId = 1L;

        // Create simulated instances of User and Query
        User user = new User();
        user.setId(1L);
        user.setUsername("exampleUser");

        Query query = new Query();
        query.setId(1L);
        query.setQueryName("exampleQuery");
        query.setDescription("Example Description");

        // Create simulated instances of QueriesSave
        List<QueriesSave> queriesSaveList = Arrays.asList(
                new QueriesSave(user, query),
                new QueriesSave(user, query)
        );

        // Mock the service to return the simulated list
        when(queriesSaveService.getQueriesByUserId(userId)).thenReturn(queriesSaveList);

        // Act
        ResponseEntity<List<QueriesSave>> responseEntity = queriesSaveController.getQueriesByUserId(userId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(queriesSaveList, responseEntity.getBody());
        verify(queriesSaveService, times(1)).getQueriesByUserId(userId);
    }

    /**
     * Unit test for the getQueriesByNameUser method of QueriesSaveController.
     */
    @Test
    void getQueriesByNameUser() {
        // Arrange
        String username = "testUser";

        // Create simulated instances of QueryDto
        List<QueryDto> queryDtoList = Arrays.asList(
                new QueryDto(1L, "Query1", "Description1", new Date(), "Term1", "Country1", 10, username),
                new QueryDto(2L, "Query2", "Description2", new Date(), "Term2", "Country2", 20, username)
        );
        // Mock the service to return the simulated list
        when(queriesSaveService.getQueriesByNameUser(username)).thenReturn(queryDtoList);

        // Act
        ResponseEntity<List<QueryDto>> responseEntity = queriesSaveController.getQueriesByNameUser(username);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(queryDtoList, responseEntity.getBody());
        verify(queriesSaveService, times(1)).getQueriesByNameUser(username);
    }

    /**
     * Unit test for the saveQuery method of QueriesSaveController.
     *
     * @throws ChangeSetPersister.NotFoundException if the entity is not found
     */
    @Test
    void saveQuery() throws ChangeSetPersister.NotFoundException {
        // Arrange
        String username = "testUser";
        Long queryId = 1L;

        // Create simulated instance of QueriesSaveDto using the utility
        QueriesSaveDto request = new QueriesSaveDto(username, queryId);

        // Create simulated instances of User and Query (adjust these values according to your model)
        User user = new User(username);
        Query query = new Query("QueryName", "Description", new Date(), "SearchTerm", "Country", 100, user);

        // Create simulated instance of QueriesSave using the utility
        QueriesSave savedQuery = new QueriesSave(user, query);

        // Mock the service to return the simulated instance
        when(queriesSaveService.saveQuery(username, queryId)).thenReturn(savedQuery);

        // Act
        ResponseEntity<QueriesSave> responseEntity = queriesSaveController.saveQuery(request);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedQuery, responseEntity.getBody());
        verify(queriesSaveService, times(1)).saveQuery(username, queryId);
    }

    /**
     * Unit test for the deleteSavedQuery method of QueriesSaveController.
     */
    @Test
    void deleteSavedQuery() {
        // Arrange
        Long saveId = 1L;

        // Act
        ResponseEntity<String> responseEntity = queriesSaveController.deleteSavedQuery(saveId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Saved query successfully deleted", responseEntity.getBody());
        verify(queriesSaveService, times(1)).deleteSavedQuery(saveId);
    }
}
