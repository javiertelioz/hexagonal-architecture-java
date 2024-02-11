package com.inicions.tasks;

import com.inicions.tasks.domain.model.User;
import com.inicions.tasks.infrastructure.repositories.user.JpaUserRepositoryAdapter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
@SecurityScheme(
		scheme = "bearer",
		bearerFormat = "JWT",
		type = SecuritySchemeType.HTTP,
		name = "Bearer Authentication",
		in = SecuritySchemeIn.HEADER
)
public class TasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

	BCryptPasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();;

	@Autowired
	JpaUserRepositoryAdapter userRepository;

	/*@Bean
	CommandLineRunner init(){
		return args -> {
			User userEntity = new User();

			userEntity.setFirsName("joe");
			userEntity.setLastName("doe");
			userEntity.setPhone("55555555555");
			userEntity.setEmail("joe_doe@gmail.com");
			userEntity.setUsername("joe");
			userEntity.setPassword(passwordEncoder.encode("1234"));
			userEntity.setCreationDate(LocalDate.now().atStartOfDay());
			userRepository.save(userEntity);
		};
	}*/

}
