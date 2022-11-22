package com.iranLabs.assignment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.iranLabs.assignment.*"})
@EntityScan(basePackages = {"com.iranLabs.assignment.persistence.entity"})
@EnableJpaRepositories(basePackages = {"com.iranLabs.assignment.persistence.repository"})
@OpenAPIDefinition(info = @Info(title = "Iran Labs api", version = "1.0", description = "api document for Iran Labs assignment"))
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

}
