package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {
	private final RoleService roleService;
	private final UserService userService;

	@Autowired
	public SpringBootSecurityDemoApplication(RoleService roleService, UserService userService) {
		this.roleService = roleService;
		this.userService = userService;
	}
	@PostConstruct
	public void startDataBase() {
		Role roleAdmin = new Role("ROLE_ADMIN");
		Role roleUser = new Role("ROLE_USER");
		Set<Role> adminSet = new HashSet<>();
		Set<Role> userSet = new HashSet<>();

		roleService.addRole(roleAdmin);
		roleService.addRole(roleUser);

		adminSet.add(roleAdmin);
		adminSet.add(roleUser);
		userSet.add(roleUser);

		User admin = new User("Akim", "Parish", (byte) 24,  "admin", "admin", adminSet);
		User user = new User("Dasha", "Morozova", (byte) 26, "user1", "111111", userSet);

		userService.addUser(admin);
		userService.addUser(user);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

}
