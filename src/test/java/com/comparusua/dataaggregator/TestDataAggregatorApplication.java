package com.comparusua.dataaggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestDataAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.from(DataAggregatorApplication::main).with(TestDataAggregatorApplication.class).run(args);
	}

}
