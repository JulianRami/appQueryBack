package com.appQueries.versionOne.controller;

import com.appQueries.versionOne.model.QueryData;
import com.appQueries.versionOne.model.Search;
import com.appQueries.versionOne.service.BigQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search-query-controller")
public class SearchQueryController {

    @Autowired
    private BigQueryService bigQueryService;

    /**
     * Executes a query for all parameters.
     *
     * @param search Search object containing parameters.
     * @return List of QueryData objects.
     * @throws InterruptedException If the operation is interrupted.
     */
    @GetMapping("/queryForAllParameters")
    public List<QueryData> queryForAllParameters(Search search) throws InterruptedException {
        return bigQueryService.getQueryForAllParameters(search.getCountry(), search.getDate(), search.getTerm(), search.getLimit());
    }

    /**
     * Executes a query for date and limit.
     *
     * @param search Search object containing parameters.
     * @return List of QueryData objects.
     * @throws InterruptedException If the operation is interrupted.
     */
    @GetMapping("/queryForDateAndLimit")
    public List<QueryData> queryForDateAndLimit(Search search) throws InterruptedException {
        return bigQueryService.getQueryForDateAndLimit(search.getDate(), search.getLimit());
    }

    /**
     * Executes a query for country, date, and limit.
     *
     * @param search Search object containing parameters.
     * @return List of QueryData objects.
     * @throws InterruptedException If the operation is interrupted.
     */
    @GetMapping("/queryForCountryDateLimit")
    public List<QueryData> queryForCountryDateLimit(Search search) throws InterruptedException {
        return bigQueryService.getQueryForCountryDateLimit(search.getCountry(), search.getDate(), search.getLimit());
    }

    /**
     * Executes a query for country and limit.
     *
     * @param search Search object containing parameters.
     * @return List of QueryData objects.
     * @throws InterruptedException If the operation is interrupted.
     */
    @GetMapping("/queryForCountryLimit")
    public List<QueryData> queryForCountryLimit(Search search) throws InterruptedException {
        return bigQueryService.getQueryForCountryLimit(search.getCountry(), search.getLimit());
    }

    /**
     * Executes a query for term and limit.
     *
     * @param search Search object containing parameters.
     * @return List of QueryData objects.
     * @throws InterruptedException If the operation is interrupted.
     */
    @GetMapping("/queryForTermLimit")
    public List<QueryData> queryForTermLimit(Search search) throws InterruptedException {
        return bigQueryService.getQueryForTermLimit(search.getTerm(), search.getLimit());
    }

    /**
     * Executes a query for country, term, and limit.
     *
     * @param search Search object containing parameters.
     * @return List of QueryData objects.
     * @throws InterruptedException If the operation is interrupted.
     */
    @GetMapping("/queryForCountryAndTermLimit")
    public List<QueryData> queryForCountryAndTermLimit(Search search) throws InterruptedException {
        return bigQueryService.getQueryForCountryAndTermLimit(search.getCountry(), search.getTerm(), search.getLimit());
    }

    /**
     * Executes a query for date, term, and limit.
     *
     * @param search Search object containing parameters.
     * @return List of QueryData objects.
     * @throws InterruptedException If the operation is interrupted.
     */
    @GetMapping("/queryForWeekAndTermLimit")
    public List<QueryData> queryForWeekAndTermLimit(Search search) throws InterruptedException {
        return bigQueryService.getQueryForDateAndTermLimit(search.getDate(), search.getTerm(), search.getLimit());
    }
}
