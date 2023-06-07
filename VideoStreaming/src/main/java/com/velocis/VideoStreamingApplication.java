package com.velocis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.velocis.model.FileStorageProperties;

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class VideoStreamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoStreamingApplication.class, args);
	}

}
