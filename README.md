# AppQueryBack

This project connects to the bigQuery API and makes requests to search trends taking into account the filters that are available, returning data necessary to make graphs of these trends, 

This project was generated with [Spring Boot].

## To create the data base

Make sure you have mysql installed.

Go to the BD folder at the root of the project, inside is a txt file with the database scripts, Create the database.

## Get access to the BigQuery API

Use a google account and go bigQuery, create an access key to the google trends api to make google trends requests with the necessary permissions, download it in json format.

Paste the key into the address \src\main\resources\google-cloud

Put your key name in the file \src\main\java\com\appQueries\versionOne\config/BigQueryConfig.java in the line "ClassPathResource resource = new ClassPathResource("key_name.json");"

## Execute

Clone the repository

Navigate to the project directory

Configure the database by editing the application.properties file and DatabaseConfig file

Configure the cors by editing the corsConfig file where you must assign where requests will be made from

Compile the project with the following command `mvn clean install`

Run the application with the command `mvn spring-boot:run`

Access the API from the browser or tools like Postman, for example in the browser with the link `http://localhost:8080/swagger-ui/swagger-ui/index.html#/`

## Create jar and run it

Run `mvn package -DskipTests` in the project root to create the application .jar without running the tests since they are not working yet.

Open a console in the target folder of the project and run the jar with the command `java -jar versionOne-0.0.1-SNAPSHOT.jar`.

Navigate to `http://localhost:8080/swagger-ui/swagger-ui/index.html#/` where you will see the swagger interface and can interact with the backend.



