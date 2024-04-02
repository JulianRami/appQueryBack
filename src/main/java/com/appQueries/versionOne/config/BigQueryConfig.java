package com.appQueries.versionOne.config;

        import com.google.auth.oauth2.GoogleCredentials;
        import com.google.cloud.bigquery.BigQuery;
        import com.google.cloud.bigquery.BigQueryOptions;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.core.io.ClassPathResource;

        import java.io.IOException;
        import java.io.InputStream;

@Configuration
public class BigQueryConfig {

    /**
     * Configures and provides a BigQuery instance with the necessary credentials.
     *
     * @return BigQuery instance
     * @throws IOException if an I/O error occurs while reading the credentials file
     */
    @Bean
    public BigQuery bigQuery() throws IOException {
        ClassPathResource resource = new ClassPathResource("google-cloud/valiant-hub-404718-6e1c95d088cf.json");
        InputStream inputStream = resource.getInputStream();

        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream)
                .createScoped("https://www.googleapis.com/auth/cloud-platform");

        return BigQueryOptions.newBuilder()
                .setCredentials(credentials)
                .build()
                .getService();
    }

}

