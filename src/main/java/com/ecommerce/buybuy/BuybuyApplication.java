package com.ecommerce.buybuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ecommerce.buybuy.repository")
@EntityScan(basePackages = "com.ecommerce.buybuy.entity")
public class BuybuyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuybuyApplication.class, args);
	}

}
