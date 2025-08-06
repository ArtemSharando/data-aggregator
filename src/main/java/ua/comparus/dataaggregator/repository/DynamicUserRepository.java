package ua.comparus.dataaggregator.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import ua.comparus.dataaggregator.config.DataSourceConfig;
import ua.comparus.dataaggregator.model.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class DynamicUserRepository {
    public List<User> fetchUsers(DataSourceConfig.CustomDataSource config) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(createDataSource(config));

        String sql = "SELECT * FROM " + config.getTable();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        return rows.stream()
                .map(row -> User.fromRow(row, config.getMapping()))
                .collect(Collectors.toList());
    }

    private DataSource createDataSource(DataSourceConfig.CustomDataSource config) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUser());
        dataSource.setPassword(config.getPassword());
        return dataSource;
    }
}
