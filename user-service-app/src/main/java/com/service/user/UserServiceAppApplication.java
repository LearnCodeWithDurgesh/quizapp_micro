package com.service.user;

import com.service.user.entities.User;
import com.service.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceAppApplication.class, args);
	}


	@Autowired
	private UserService userService;


	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setUserName("user1");
		user1.setUserEmail("user1@example.com");
		user1.setUserPassword("password1");
		user1.setUserAbout("User 1 details");

		User user2 = new User();
		user2.setUserName("user2");
		user2.setUserEmail("user2@example.com");
		user2.setUserPassword("password2");
		user2.setUserAbout("User 2 details");

		User user3 = new User();
		user3.setUserName("user3");
		user3.setUserEmail("user3@example.com");
		user3.setUserPassword("password3");
		user3.setUserAbout("User 3 details");

		// Save default users
		userService.saveUser(user1);
		userService.saveUser(user2);
		userService.saveUser(user3);
	}
}
