package com.exam;

import com.exam.helper.UserFoundException;
import com.exam.models.Role;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@ComponentScan("com.exam")
public class ExamportalApplication implements CommandLineRunner {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) {

		SpringApplication.run(ExamportalApplication.class, args);
		System.out.println("Hello Exam Portal Project");
	}

	@Override
	public void run(String... args) throws Exception {

		try {
			System.out.println("starting the project");
			User user = new User();
			user.setUsername("ravi563");
			user.setEmail("mauryaravikant555@gmail.com@gmail.com");
			user.setEnabled(true);
			user.setFname("Ravikant");
			user.setLname("Maurya");
			user.setPhone("7023225567");
			user.setPassword(bCryptPasswordEncoder.encode("Ravi123@"));
			user.setProfile("image.png");

			Role role = new Role();
			role.setRoleName("ADMIN");
			role.setRole_id(100L);


			UserRole userRole = new UserRole();
			userRole.setRole(role);
			userRole.setUser(user);
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(userRole);


			User user1 = this.userService.createUser(user, userRoles);
		
			System.out.println(user1.getUsername() + " Created user");
		}catch (UserFoundException e)
		{
			e.printStackTrace();
		}
	}
}
