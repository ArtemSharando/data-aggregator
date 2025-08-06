package ua.comparus.dataaggregator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "data-sources")
@Data
public class DataSourceConfig {

    private List<CustomDataSource> dataSources;

    @Data
    public static class CustomDataSource {
        private String name;
        private String strategy;
        private String url;
        private String user;
        private String password;
        private String table;
        private Map<String, String> mapping;
    }
}
