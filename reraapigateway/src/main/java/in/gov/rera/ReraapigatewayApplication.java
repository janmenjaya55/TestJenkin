package in.gov.rera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import in.gov.rera.filters.PostFilter;


@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableHystrix
public class ReraapigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReraapigatewayApplication.class, args);
	}
	@Bean
	public PostFilter getPostFilter(){
	    return new PostFilter();
	}
}
