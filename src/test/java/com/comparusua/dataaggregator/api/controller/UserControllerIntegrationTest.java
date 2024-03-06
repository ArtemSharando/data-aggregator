package com.comparusua.dataaggregator.api.controller;

import com.comparusua.dataaggregator.database.db1.repository.DB1UserRepository;
import com.comparusua.dataaggregator.database.db2.repository.DB2UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Container
    public static PostgreSQLContainer<?> db1Container = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("test_db1")
            .withUsername("postgres")
            .withPassword("test");

    @Container
    public static PostgreSQLContainer<?> db2Container = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("test_db2")
            .withUsername("postgres")
            .withPassword("test");

    @Autowired
    private DB1UserRepository db1UserRepository;

    @Autowired
    private DB2UserRepository db2UserRepository;

    @BeforeEach
    public void setup() {
        db1UserRepository.deleteAll();
        db2UserRepository.deleteAll();

        com.comparusua.dataaggregator.database.db1.entity.User user1 = new com.comparusua.dataaggregator.database.db1.entity.User();
        user1.setUsername("test1");
        user1.setName("Test");
        user1.setSurname("User1");
        db1UserRepository.save(user1);


        com.comparusua.dataaggregator.database.db2.entity.User user2 = new com.comparusua.dataaggregator.database.db2.entity.User();
        user2.setUsername("test2");
        user2.setName("Test");
        user2.setSurname("User2");
        db2UserRepository.save(user2);
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", db1Container::getJdbcUrl);
        registry.add("spring.datasource.password", db1Container::getPassword);
        registry.add("spring.datasource.username", db1Container::getUsername);

        registry.add("spring.second-datasource.url", db2Container::getJdbcUrl);
        registry.add("spring.second-datasource.password", db2Container::getPassword);
        registry.add("spring.second-datasource.username", db2Container::getUsername);
    }

    @Test
    public void testGetAllUsers() {
        given().port(port)
                .when().get("/api/users")
                .then().statusCode(200)
                .body("$", hasSize(2));
    }

    @Test
    public void testGetAllUsersByUsername() {
        String username = "test1";

        given().port(port)
                .param("username", username)
                .when().get("/api/users")
                .then().statusCode(200)
                .body("$", hasSize(1));
    }
}