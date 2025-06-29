package com.learnwithiftekhar.spring_jwt_demo;

import com.learnwithiftekhar.spring_jwt_demo.model.User;
import com.learnwithiftekhar.spring_jwt_demo.ropository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringJwtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository repository, PasswordEncoder passwordEncoder) {
		return args -> {
			User user = new User();
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("admin"));
			repository.save(user);
		};
	}
}
