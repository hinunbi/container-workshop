package com.redhat.cats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ImportResource({ "classpath:spring/camel-context.xml", "classpath:spring/amq.xml" })
@EntityScan("com.redhat.cats.model")
@EnableJpaRepositories("com.redhat.cats.repository")
public class CatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatsApplication.class, args);
	}
}
