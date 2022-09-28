package com.app.auction;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.auction.config.AppConstants;
import com.app.auction.entities.Role;
import com.app.auction.repositories.RoleRepository;

@SpringBootApplication 
@EnableScheduling
public class QuickAuctionApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder password;
	@Autowired
	private RoleRepository rolerepo;

	public static void main(String[] args) {
		SpringApplication.run(QuickAuctionApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.password.encode("f#123"));
		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN);
			role.setRoleName("ADMIN");
			Role role2 = new Role();
			role2.setId(AppConstants.USER);
			role2.setRoleName("USER");
			List<Role> roles = List.of(role, role2);
			List<Role> result = rolerepo.saveAll(roles);
			result.forEach(r -> {
				System.out.println(r.getRoleName());
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
