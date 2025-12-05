package com.mahir.stack2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.mahir" })
@EntityScan({ "com.mahir" })
@EnableJpaRepositories({"com.mahir"})
public class Stack2Application {

	public static void main(String[] args) {
		SpringApplication.run(Stack2Application.class, args);
	}

}
