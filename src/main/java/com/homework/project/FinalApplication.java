package com.homework.project;

import com.homework.project.domain.*;
import com.homework.project.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.time.Instant;

import static com.homework.project.GeneralConstants.SYSTEM_USER;

@SpringBootApplication
@AllArgsConstructor
@EnableConfigurationProperties(ApplicationProperties.class)
public class FinalApplication implements CommandLineRunner {

	private final UsersService usersService;

	public static void main(String[] args) {
		SpringApplication.run(FinalApplication.class, args);
	}

	@Override
	public void run(String... args) {
		final Instant now = Instant.now();
		final Position position = new Position();
		position.setName("admin");
		position.setCreatedBy(SYSTEM_USER);
		position.setLastModifiedBy(SYSTEM_USER);

		final Department department = new Department();
		department.setName("administration");
		department.setCreatedBy(SYSTEM_USER);
		department.setLastModifiedBy(SYSTEM_USER);

		final User user = new User();
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setEmail("admin@admin.com");
		user.setPassword("admin");
		user.setType(UserType.ADMIN);
		user.setCreatedBy(SYSTEM_USER);
		user.setCreatedDate(now);
		user.setLastModifiedBy(SYSTEM_USER);
		user.setLastModifiedDate(now);
		user.setHourSalary(10D);
		user.setPosition(position);
		user.setDepartment(department);
		user.setStatus(UserStatus.WORKING);
		usersService.create(user);
	}
}
