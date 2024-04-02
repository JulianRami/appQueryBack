package com.appQueries.versionOne.Controller;

import com.appQueries.versionOne.controller.SearchQueryController;
import com.appQueries.versionOne.model.QueryData;
import com.appQueries.versionOne.model.Search;
import com.appQueries.versionOne.service.BigQueryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
class SearchQueryControllerTest {

    @Mock
    private BigQueryService bigQueryService;

    @InjectMocks
    private SearchQueryController searchQueryController;

    /**
     * Test method to verify the functionality of executing a query for all parameters.
     * It simulates a call to the BigQueryService's 'getQueryForAllParameters' method and asserts the expected result.
     *
     * @throws InterruptedException if the operation is interrupted.
     */
    @Test
    void queryForAllParameters() throws InterruptedException {
        Search search = new Search("Country1", "2023-11-21", "Term1", 10);
        List<QueryData> queryDataList = Arrays.asList(
                new QueryData(new Date(), "Term1", 1, 90, "Country1"),
                new QueryData(new Date(), "Term2", 2, 85, "Country1")
        );

        when(bigQueryService.getQueryForAllParameters(search.getCountry(), search.getDate(), search.getTerm(), search.getLimit())).thenReturn(queryDataList);
        List<QueryData> result = searchQueryController.queryForAllParameters(search);
        assertEquals(queryDataList, result);
        verify(bigQueryService, times(1)).getQueryForAllParameters(search.getCountry(), search.getDate(), search.getTerm(), search.getLimit());
    }
    /**
     * Test method to verify the functionality of executing a query for date and limit.
     * It simulates a call to the BigQueryService's 'getQueryForDateAndLimit' method and asserts the expected result.
     *
     * @throws InterruptedException if the operation is interrupted.
     */
    @Test
    void queryForDateAndLimit() throws InterruptedException {
        Search search = new Search("Country1", "2023-11-21", null, 10);
        List<QueryData> queryDataList = Arrays.asList(
                new QueryData(new Date(), "Term1", 1, 90, "Country1"),
                new QueryData(new Date(), "Term2", 2, 85, "Country1")
        );

        when(bigQueryService.getQueryForDateAndLimit(search.getDate(), search.getLimit())).thenReturn(queryDataList);
        List<QueryData> result = searchQueryController.queryForDateAndLimit(search);
        assertEquals(queryDataList, result);
        verify(bigQueryService, times(1)).getQueryForDateAndLimit(search.getDate(), search.getLimit());
    }

    /**
     * Test method to verify the functionality of executing a query for country, date, and limit.
     * It simulates a call to the BigQueryService's 'getQueryForCountryDateLimit' method and asserts the expected result.
     *
     * @throws InterruptedException if the operation is interrupted.
     */
    @Test
    void queryForCountryDateLimit() throws InterruptedException {
        Search search = new Search("Country1", "2023-11-21", null, 10);
        List<QueryData> queryDataList = Arrays.asList(
                new QueryData(new Date(), "Term1", 1, 90, "Country1"),
                new QueryData(new Date(), "Term2", 2, 85, "Country1")
        );

        when(bigQueryService.getQueryForCountryDateLimit(search.getCountry(), search.getDate(), search.getLimit())).thenReturn(queryDataList);
        List<QueryData> result = searchQueryController.queryForCountryDateLimit(search);
        assertEquals(queryDataList, result);
        verify(bigQueryService, times(1)).getQueryForCountryDateLimit(search.getCountry(), search.getDate(), search.getLimit());
    }
    /**
     * Test class for the SearchQueryController, focusing on the 'queryForCountryLimit' method.
     * It utilizes Mockito to mock the BigQueryService and injects it into the SearchQueryController.
     */
    @Test
    void queryForCountryLimit() throws InterruptedException {
        Search search = new Search("Country1", null, null, 10);
        List<QueryData> queryDataList = Arrays.asList(
                new QueryData(new Date(), "Term1", 1, 90, "Country1"),
                new QueryData(new Date(), "Term2", 2, 85, "Country1")
        );
        when(bigQueryService.getQueryForCountryLimit(search.getCountry(), search.getLimit())).thenReturn(queryDataList);
        List<QueryData> result = searchQueryController.queryForCountryLimit(search);
        assertEquals(queryDataList, result);
        verify(bigQueryService, times(1)).getQueryForCountryLimit(search.getCountry(), search.getLimit());
    }

    /**
     * Test method to verify the functionality of executing a query for term and limit.
     * It simulates a call to the BigQueryService's 'getQueryForTermLimit' method and asserts the expected result.
     *
     * @throws InterruptedException if the operation is interrupted.
     */
    @Test
    void queryForTermLimit() throws InterruptedException {
        Search search = new Search(null, null, "SampleTerm", 10);
        List<QueryData> queryDataList = Arrays.asList(
                new QueryData(new Date(), "SampleTerm", 1, 90, "Country1"),
                new QueryData(new Date(), "SampleTerm", 2, 85, "Country2")
        );
        when(bigQueryService.getQueryForTermLimit(search.getTerm(), search.getLimit())).thenReturn(queryDataList);
        List<QueryData> result = searchQueryController.queryForTermLimit(search);
        assertEquals(queryDataList, result);
        verify(bigQueryService, times(1)).getQueryForTermLimit(search.getTerm(), search.getLimit());
    }

    /**
     * Test method to verify the functionality of executing a query for country, term, and limit.
     * It simulates a call to the BigQueryService's 'getQueryForCountryAndTermLimit' method and asserts the expected result.
     *
     * @throws InterruptedException if the operation is interrupted.
     */
    @Test
    void queryForCountryAndTermLimit() throws InterruptedException {
        Search search = new Search("Country1", null, "SampleTerm", 10);
        List<QueryData> queryDataList = Arrays.asList(
                new QueryData(new Date(), "SampleTerm", 1, 90, "Country1"),
                new QueryData(new Date(), "SampleTerm", 2, 85, "Country1")
        );
        when(bigQueryService.getQueryForCountryAndTermLimit(search.getCountry(), search.getTerm(), search.getLimit())).thenReturn(queryDataList);
        List<QueryData> result = searchQueryController.queryForCountryAndTermLimit(search);
        assertEquals(queryDataList, result);
        verify(bigQueryService, times(1)).getQueryForCountryAndTermLimit(search.getCountry(), search.getTerm(), search.getLimit());
    }

    /**
     * Test method to verify the functionality of executing a query for date, term, and limit.
     * It simulates a call to the BigQueryService's 'getQueryForDateAndTermLimit' method and asserts the expected result.
     *
     * @throws InterruptedException if the operation is interrupted.
     */
    @Test
    void queryForWeekAndTermLimit() throws InterruptedException {
        Search search = new Search("Country1", "2023-01-01", "SampleTerm", 10);
        List<QueryData> queryDataList = Arrays.asList(
                new QueryData(new Date(), "SampleTerm", 1, 90, "Country1"),
                new QueryData(new Date(), "SampleTerm", 2, 85, "Country1")
        );
        when(bigQueryService.getQueryForDateAndTermLimit(search.getDate(), search.getTerm(), search.getLimit())).thenReturn(queryDataList);
        List<QueryData> result = searchQueryController.queryForWeekAndTermLimit(search);
        assertEquals(queryDataList, result);
        verify(bigQueryService, times(1)).getQueryForDateAndTermLimit(search.getDate(), search.getTerm(), search.getLimit());
    }

}

