# Data Aggregator Service

A Spring Boot service that aggregates user data from multiple PostgreSQL databases.

## How to Run

### 1. Build the project

```bash
mvn clean package
```

### 2. Start PostgreSQL databases
Use Docker:
```bash
docker-compose up -d
```

### 3. Add tables and some data to db

CREATE TABLE users (
user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
login VARCHAR(50),
first_name VARCHAR(100),
last_name VARCHAR(100)
);

INSERT INTO users (login, first_name, last_name)
VALUES
('user1', 'Alice', 'Smith'),
('user2', 'Bob', 'Johnson');

### Swagger UI
http://localhost:8080/swagger-ui.html
