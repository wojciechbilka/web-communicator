package com.communicator.webcommunicator;

import com.communicator.webcommunicator.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class WebCommunicatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCommunicatorApplication.class, args);
	}

}
