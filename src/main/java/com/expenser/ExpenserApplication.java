package com.expenser;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScans({ @ComponentScan("com.expenser.controller"), @ComponentScan("com.expenser.config"),
		@ComponentScan("com.expenser.secuirty"), @ComponentScan("com.expenser.eventListener") })
@EntityScan("com.expenser.Entity")
@EnableJpaRepositories("com.expenser.repository")
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class ExpenserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenserApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
