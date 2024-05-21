package com.customer.rewards.points;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableMongoRepositories
//@EnableSwagger2
public class RewardPointsCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardPointsCalculatorApplication.class, args);
	}

}
