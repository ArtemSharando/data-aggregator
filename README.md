# data-aggregator

An application to extract information about users from multiple databases

## Requirements

For building and running the application you need:

- [JDK 17]
- [Gradle]

## Running the application  
Before running the application, you need to run the docker file `docker-compose.yml` located in the resources package  

There are several ways to run an app on your local machine. One way is to execute the `main` method in the `com.comparusua.dataaggregator.DataAggregatorApplication` class from your IDE.

Alternatively you can use the terminal with command:

```shell
./gradlew bootRun
```
## Endpoints

Get all users endpoint:  
http://localhost:8080/api/users

You can also add the parameters username, name, and surname by which users will be filtered  
http://localhost:8080/api/users?username=user-1

Additionally, to enhance the handling of large data volumes, there was implemented to retrieve users on a page-by-page basis  
http://localhost:8080/api/users&page=0&size=10

Swagger documentation link:  
http://localhost:8080/swagger-ui/index.html  

## Test  
Before running the tests, you need to run the docker file located in the test resources.
To run tests use the class from your IDE `com.comparusua.dataaggregator.api.controller.UserControllerIntegrationTest`
