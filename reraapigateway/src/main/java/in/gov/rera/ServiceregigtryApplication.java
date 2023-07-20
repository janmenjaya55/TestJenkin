package in.gov.rera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceregigtryApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ServiceregigtryApplication.class, args);
	}

}
