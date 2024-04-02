package com.appQueries.versionOne.service;

import com.appQueries.versionOne.model.QueryData;
import com.google.cloud.bigquery.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
/**
 * The `BigQueryService` class provides methods for querying Google BigQuery to retrieve data based on specified parameters.
 */
@Service
public class BigQueryService {
    @Autowired
    private BigQuery bigQuery;

    public BigQueryService() {
    }
    /**
     * Retrieves query data for all parameters (country, date, term, limit).
     *
     * @param country The country for the query.
     * @param date    The date for the query.
     * @param term    The search term for the query.
     * @param limit   The limit for the number of results.
     * @return The list of query data.
     * @throws BigQueryException      If an error occurs during the BigQuery execution.
     * @throws InterruptedException   If the execution is interrupted.
     */
    public List<QueryData> getQueryForAllParameters(String country, String date, String term, int limit) throws BigQueryException, InterruptedException {
        String query = String.format("WITH ranked_data AS (" +
                "  SELECT" +
                "    country_name," +
                "    term," +
                "    week," +
                "    score," +
                "    rank," +
                "    ROW_NUMBER() OVER (PARTITION BY country_name, term, week ORDER BY score DESC) as row_num" +
                "  FROM `bigquery-public-data.google_trends.international_top_terms`" +
                "  WHERE country_name = '%s' AND week = DATE '%s' AND term = '%s' AND score IS NOT NULL AND rank IS NOT NULL" +
                ")" +
                "SELECT" +
                "  country_name," +
                "  term," +
                "  week," +
                "  score," +
                "  rank" +
                " FROM ranked_data" +
                " WHERE" +
                "  row_num = 1" +
                " LIMIT %d", country, date, term, limit);

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
        TableResult result = bigQuery.query(queryConfig);

        return parseResult(result);
    }
    /**
     * Retrieves query data for date and limit parameters.
     *
     * @param date  The date for the query.
     * @param limit The limit for the number of results.
     * @return The list of query data.
     * @throws BigQueryException    If an error occurs during the BigQuery execution.
     * @throws InterruptedException If the execution is interrupted.
     */
    public List<QueryData> getQueryForDateAndLimit(String date, int limit) throws BigQueryException, InterruptedException {
        String query = String.format(
                "WITH ranked_data AS (" +
                        "  SELECT" +
                        "    country_name," +
                        "    term," +
                        "    week," +
                        "    score," +
                        "    rank," +
                        "    ROW_NUMBER() OVER (PARTITION BY country_name, term ORDER BY rank ASC) as row_num" +
                        "  FROM `bigquery-public-data.google_trends.international_top_terms`" +
                        "  WHERE week = DATE '%s' AND score IS NOT NULL AND rank IS NOT NULL" +
                        ")" +
                        "SELECT" +
                        "  country_name," +
                        "  term," +
                        "  week," +
                        "  score," +
                        "  rank" +
                        " FROM ranked_data" +
                        " WHERE" +
                        "  row_num = 1" +
                        " ORDER BY rank ASC" +  // Ordenar por score de mayor a menor
                        " LIMIT %d", date, limit);

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
        TableResult result = bigQuery.query(queryConfig);

        return parseResult(result);
    }
    /**
     * Retrieves query data for country, date, and limit parameters.
     *
     * @param country The country for the query.
     * @param date    The date for the query.
     * @param limit   The limit for the number of results.
     * @return The list of query data.
     * @throws BigQueryException    If an error occurs during the BigQuery execution.
     * @throws InterruptedException If the execution is interrupted.
     */
    public List<QueryData> getQueryForCountryDateLimit(String country, String date, int limit) throws BigQueryException, InterruptedException {
        String query = String.format(
                "WITH ranked_data AS (" +
                        "  SELECT" +
                        "    country_name," +
                        "    term," +
                        "    week," +
                        "    score," +
                        "    rank," +
                        "    ROW_NUMBER() OVER (PARTITION BY country_name, term ORDER BY rank ASC) as row_num" +
                        "  FROM `bigquery-public-data.google_trends.international_top_terms`" +
                        "  WHERE country_name = '%s' AND week = DATE '%s' AND score IS NOT NULL AND rank IS NOT NULL" +
                        ")" +
                        "SELECT" +
                        "  country_name," +
                        "  term," +
                        "  week," +
                        "  score," +
                        "  rank" +
                        " FROM ranked_data" +
                        " WHERE" +
                        "  row_num = 1" +
                        " ORDER BY score DESC, rank ASC" +  // Ordenar por score de mayor a menor
                        " LIMIT %d", country, date, limit);

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
        TableResult result = bigQuery.query(queryConfig);

        return parseResult(result);
    }
    /**
     * Retrieves query data for country and limit parameters.
     *
     * @param country The country for the query.
     * @param limit   The limit for the number of results.
     * @return The list of query data.
     * @throws BigQueryException    If an error occurs during the BigQuery execution.
     * @throws InterruptedException If the execution is interrupted.
     */
    public List<QueryData> getQueryForCountryLimit(String country, int limit) throws BigQueryException, InterruptedException {
        String query = String.format(
                "WITH ranked_data AS (" +
                        "  SELECT" +
                        "    country_name," +
                        "    term," +
                        "    week," +
                        "    score," +
                        "    rank," +
                        "    ROW_NUMBER() OVER (PARTITION BY country_name, term ORDER BY rank ASC) as row_num" +
                        "  FROM `bigquery-public-data.google_trends.international_top_terms`" +
                        "  WHERE country_name = '%s' AND score IS NOT NULL AND rank IS NOT NULL" +
                        ")" +
                        "SELECT" +
                        "  country_name," +
                        "  term," +
                        "  week," +
                        "  score," +
                        "  rank" +
                        " FROM ranked_data" +
                        " WHERE" +
                        "  row_num = 1" +
                        " ORDER BY rank ASC" +  // Ordenar por score de mayor a menor
                        " LIMIT %d", country, limit);

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
        TableResult result = bigQuery.query(queryConfig);

        return parseResult(result);
    }
    /**
     * Retrieves query data for term and limit parameters.
     *
     * @param term  The search term for the query.
     * @param limit The limit for the number of results.
     * @return The list of query data.
     * @throws BigQueryException    If an error occurs during the BigQuery execution.
     * @throws InterruptedException If the execution is interrupted.
     */
    public List<QueryData> getQueryForTermLimit(String term, int limit) throws BigQueryException, InterruptedException {
        String query = String.format(
                "WITH ranked_data AS (" +
                        "  SELECT DISTINCT" +  // Utilizar DISTINCT para evitar filas duplicadas
                        "    country_name," +
                        "    term," +
                        "    week," +
                        "    score," +
                        "    rank," +
                        "    ROW_NUMBER() OVER (ORDER BY rank ASC) as row_num" +
                        "  FROM `bigquery-public-data.google_trends.international_top_terms`" +
                        "  WHERE term = '%s' AND score IS NOT NULL AND rank IS NOT NULL" +
                        ")" +
                        "SELECT" +
                        "  country_name," +
                        "  term," +
                        "  week," +
                        "  score," +
                        "  rank" +
                        " FROM ranked_data" +
                        " ORDER BY score DESC" +  // Ordenar por score de mayor a menor
                        " LIMIT %d", term, limit);

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
        TableResult result = bigQuery.query(queryConfig);

        return parseResult(result);
    }
    /**
     * Retrieves query data for country, term, and limit parameters.
     *
     * @param countryName The country for the query.
     * @param term        The search term for the query.
     * @param limit       The limit for the number of results.
     * @return The list of query data.
     * @throws BigQueryException    If an error occurs during the BigQuery execution.
     * @throws InterruptedException If the execution is interrupted.
     */
    public List<QueryData> getQueryForCountryAndTermLimit(String countryName, String term, int limit) throws BigQueryException, InterruptedException {
        String query = String.format(
                "WITH ranked_data AS (" +
                        "  SELECT" +
                        "    country_name," +
                        "    term," +
                        "    week," +
                        "    MAX(score) as score," +  // Utilizar MAX para obtener el máximo score
                        "    MIN(rank) as rank," +    // Utilizar MIN para obtener el mínimo rank
                        "    ROW_NUMBER() OVER (ORDER BY MIN(week) ASC) as row_num" +
                        "  FROM `bigquery-public-data.google_trends.international_top_terms`" +
                        "  WHERE country_name = '%s' AND term = '%s' AND score IS NOT NULL AND rank IS NOT NULL" +
                        "  GROUP BY country_name, term, week" +  // Agrupar por country_name, term y week
                        ")" +
                        "SELECT" +
                        "  country_name," +
                        "  term," +
                        "  week," +
                        "  score," +
                        "  rank" +
                        " FROM ranked_data" +
                        " LIMIT %d", countryName, term, limit);

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
        TableResult result = bigQuery.query(queryConfig);

        return parseResult(result);
    }
    /**
     * Retrieves query data for date, term, and limit parameters.
     *
     * @param week  The date for the query.
     * @param term  The search term for the query.
     * @param limit The limit for the number of results.
     * @return The list of query data.
     * @throws BigQueryException    If an error occurs during the BigQuery execution.
     * @throws InterruptedException If the execution is interrupted.
     */
    public List<QueryData> getQueryForDateAndTermLimit(String week, String term, int limit) throws BigQueryException, InterruptedException {
        String query = String.format(
                "WITH ranked_data AS (" +
                        "  SELECT" +
                        "    country_name," +
                        "    term," +
                        "    week," +
                        "    MAX(score) as score," +  // Utilizar MAX para obtener el máximo score
                        "    MIN(rank) as rank," +    // Utilizar MIN para obtener el mínimo rank
                        "    ROW_NUMBER() OVER (ORDER BY MAX(rank) ASC) as row_num" +
                        "  FROM `bigquery-public-data.google_trends.international_top_terms`" +
                        "  WHERE week = DATE '%s' AND term = '%s' AND score IS NOT NULL AND rank IS NOT NULL" +
                        "  GROUP BY country_name, term, week" +  // Agrupar por country_name, term y week
                        ")" +
                        "SELECT" +
                        "  country_name," +
                        "  term," +
                        "  week," +
                        "  score," +
                        "  rank" +
                        " FROM ranked_data" +
                        " ORDER BY score DESC" +  // Ordenar por score de mayor a menor
                        " LIMIT %d", week, term, limit);

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
        TableResult result = bigQuery.query(queryConfig);

        return parseResult(result);
    }


    /**
     * Parses the result of a BigQuery query and converts it into a list of `QueryData`.
     *
     * @param result The result of the BigQuery query.
     * @return The list of `QueryData`.
     */
    private List<QueryData> parseResult(TableResult result) {
        return StreamSupport.stream(result.iterateAll().spliterator(), false)
                .map(row -> {
                    QueryData queryData = new QueryData();
                    queryData.setSearchTerm(row.get("term").getStringValue());
                    queryData.setCountry(row.get("country_name").getStringValue());
                    queryData.setScore((int) row.get("score").getLongValue());
                    queryData.setRanking((int) row.get("rank").getLongValue());
                    String timestampString = (row.get("week").getStringValue());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date;
                    try {
                        date = dateFormat.parse(timestampString);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    queryData.setDate(date);
                    return queryData;
                })
                .collect(Collectors.toList());
    }

}

