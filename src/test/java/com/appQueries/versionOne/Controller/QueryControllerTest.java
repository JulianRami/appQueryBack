package com.appQueries.versionOne.Controller;

import com.appQueries.versionOne.controller.QueryController;
import com.appQueries.versionOne.dto.QueryDto;
import com.appQueries.versionOne.model.Query;
import com.appQueries.versionOne.model.User;
import com.appQueries.versionOne.service.QueryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
class QueryControllerTest {

    @Mock
    private QueryService queryService;

    @InjectMocks
    private QueryController queryController;

    /**
     * Test method to verify the functionality of retrieving all queries.
     * It simulates a call to the QueryService's getAllQueries method and asserts the expected result.
     */
    @Test
    void getAllQueries() {
        // Arrange
        List<QueryDto> queryDtoList = Arrays.asList(
                new QueryDto(1L, "Query1", "Description1", new Date(), "Term1", "Country1", 10, "User1"),
                new QueryDto(2L, "Query2", "Description2", new Date(), "Term2", "Country2", 20, "User2")
        );
        when(queryService.getAllQueries()).thenReturn(queryDtoList);

        // Act
        List<QueryDto> result = queryController.getAllQueries();

        // Assert
        assertEquals(queryDtoList, result);
        verify(queryService, times(1)).getAllQueries();
    }



    /**
     * Test method to verify the functionality of retrieving a query by its ID.
     * It simulates a call to the QueryService's getQueryById method and asserts the expected result.
     */
    @Test
    void getQueryById() {
        // Arrange
        Long queryId = 1L;
        Query query = new Query("QueryName", "Description", new Date(), "SearchTerm", "Country", 10, new User("UserName"));
        query.setId(queryId);

        when(queryService.getQueryById(queryId)).thenReturn(query);

        // Act
        ResponseEntity<Query> responseEntity = queryController.getQueryById(queryId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(query, responseEntity.getBody());
        verify(queryService, times(1)).getQueryById(queryId);
    }



    /**
     * Test method to verify the functionality of retrieving queries by a specific username.
     * It simulates a call to the QueryService's getQueryByName method and asserts the expected result.
     */
    @Test
    void getQueriesByNameUser() {
        // Arrange
        String username = "user1";
        List<QueryDto> queryDtoList = Arrays.asList(
                new QueryDto(1L, "Query1", "Description1", new Date(), "Term1", "Country1", 10, username),
                new QueryDto(2L, "Query2", "Description2", new Date(), "Term2", "Country2", 20, username)
        );
        when(queryService.getQueryByName(username)).thenReturn(queryDtoList);

        // Act
        ResponseEntity<List<QueryDto>> responseEntity = queryController.getQueriesByNameUser(username);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(queryDtoList, responseEntity.getBody());
        verify(queryService, times(1)).getQueryByName(username);
    }



    /**
     * Test method to verify the functionality of creating a new query.
     * It simulates a call to the QueryService's createQuery method and asserts the expected result.
     */
    @Test
    void createQuery() {
        // Arrange
        QueryDto queryDto = new QueryDto(1L, "NewQuery", "Description", new Date(), "SearchTerm", "Country", 10, "User1");
        Query createdQuery = new Query("NewQuery", "Description", new Date(), "SearchTerm", "Country", 10, new User("User1"));
        createdQuery.setId(1L);

        when(queryService.createQuery(queryDto)).thenReturn(createdQuery);

        // Act
        ResponseEntity<Query> responseEntity = queryController.createQuery(queryDto);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(createdQuery, responseEntity.getBody());
        verify(queryService, times(1)).createQuery(queryDto);
    }



    /**
     * Test method to verify the functionality of updating an existing query.
     * It simulates a call to the QueryService's updateQuery method and asserts the expected result.
     */
    @Test
    void updateQuery() {
        // Arrange
        Long queryId = 1L;
        Query queryToUpdate = new Query("QueryNameToUpdate", "DescriptionToUpdate", new Date(), "SearchTerm", "Country", 10, new User("UserToUpdate"));
        queryToUpdate.setId(queryId);

        Query updatedQuery = new Query("UpdatedQueryName", "UpdatedDescription", new Date(), "UpdatedSearchTerm", "UpdatedCountry", 20, new User("UpdatedUser"));
        updatedQuery.setId(queryId);

        when(queryService.updateQuery(queryId, queryToUpdate)).thenReturn(updatedQuery);

        // Act
        ResponseEntity<Query> responseEntity = queryController.updateQuery(queryId, queryToUpdate);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedQuery, responseEntity.getBody());
        verify(queryService, times(1)).updateQuery(queryId, queryToUpdate);
    }



    /**
     * Test method to verify the functionality of deleting a query.
     * It simulates a call to the QueryService's deleteQuery method and asserts the expected result.
     */
    @Test
    void deleteQuery() {
        // Arrange
        Long queryId = 1L;

        // Act
        ResponseEntity<Void> responseEntity = queryController.deleteQuery(queryId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(queryService, times(1)).deleteQuery(queryId);
    }


}
