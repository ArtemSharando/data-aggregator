package ua.comparus.dataaggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DataAggregatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataAggregatorApplication.class, args);
    }

}
