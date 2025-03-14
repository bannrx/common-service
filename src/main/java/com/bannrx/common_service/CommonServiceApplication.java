package com.bannrx.common_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"com.bannrx.common.service",
		"com.bannrx.common_service",
		"rklab.utility"
})
@EntityScan(basePackages = "com.bannrx.common.persistence.entities")
@EnableJpaRepositories(basePackages = "com.bannrx.common.repository")
@EnableAspectJAutoProxy
public class CommonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonServiceApplication.class, args);
	}

}
