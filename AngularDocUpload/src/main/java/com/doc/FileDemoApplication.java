package com.doc;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.doc.model.FileStorageProperties;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication

@EnableScheduling
@EnableDiscoveryClient
@EnableSwagger2
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class FileDemoApplication {

    public static void main(String[] args) {
    	SpringApplication.run(FileDemoApplication.class, args);
    }
    
//A docket is an object that contains all the customizable properties you set and is used by Swagger to generate the documentation. 
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.doc")).paths(regex("/.*")).build();
	}
	//http://localhost:5012/swagger-ui.html
	//http://localhost:5012/v2/api-docs
}