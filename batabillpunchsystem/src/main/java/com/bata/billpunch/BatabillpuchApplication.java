package com.bata.billpunch;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableSwagger2
@EnableEurekaClient
//@Profile("dev")
public class BatabillpuchApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(BatabillpuchApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// Setting Spring Boot SetTimeZone
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.bata.billpunch")).paths(regex("/.*")).build();
	}

	/*
	 * @Bean public Docket api() { return new
	 * Docket(DocumentationType.SWAGGER_2).globalOperationParameters(Arrays.asList(
	 * new ParameterBuilder() .name("Authorization").
	 * description("Result Type: JSON(application/json)-XML(application/xml)")
	 * .modelRef(new
	 * ModelRef("string")).parameterType("header").required(true).build())); }
	 */

	// http://localhost:5011/v2/api-docs (working fine for totally db str. along
	// with apis)
	// http://localhost:5011/swagger-ui.html

}
