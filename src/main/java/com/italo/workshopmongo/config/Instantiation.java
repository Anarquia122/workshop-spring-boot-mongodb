package com.italo.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.italo.workshopmongo.domain.User;
import com.italo.workshopmongo.repositiories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepo.deleteAll();
		
		User grimmjow = new User(null, "Grimmjow", "grim@gmail.com");
		User ulquiorra = new User(null, "Ulquiorra", "ulq@gmail.com");
		User stark = new User(null, "Stark", "bob@gmail.com");
		
		userRepo.saveAll(Arrays.asList(grimmjow, ulquiorra, stark));
	}

}
