package com.smartfarm.smartfarm;

import com.smartfarm.smartfarm.entity.Role;
import com.smartfarm.smartfarm.entity.User;
import com.smartfarm.smartfarm.repositories.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class SmartfarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartfarmApplication.class, args);
	}

	@Bean
	public CommandLineRunner createAdmin(UserRepo userRepo, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepo.findByEmail("admin@smartfarm.com").isEmpty()) {
				User admin = User.builder()
						.name("Admin")
						.email("admin@smartfarm.com")
						.password(passwordEncoder.encode("Admin@123"))
						.role(Role.ADMIN)
						.build();
				userRepo.save(admin);
				System.out.println("✅ Admin created: admin@smartfarm.com / Admin@123");
			} else {
				System.out.println("ℹ️ Admin already exists");
			}
		};
	}
}