package com.italo.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.italo.workshopmongo.domain.Post;
import com.italo.workshopmongo.domain.User;
import com.italo.workshopmongo.dto.AuthorDTO;
import com.italo.workshopmongo.dto.CommentDTO;
import com.italo.workshopmongo.repositiories.PostRepository;
import com.italo.workshopmongo.repositiories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		userRepo.deleteAll();
		postRepo.deleteAll();
		
		User grimmjow = new User(null, "Grimmjow", "grim@gmail.com");
		User ulquiorra = new User(null, "Ulquiorra", "ulq@gmail.com");
		User stark = new User(null, "Stark", "bob@gmail.com");
		
		userRepo.saveAll(Arrays.asList(grimmjow, ulquiorra, stark));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Saindo do Hueco mundo", "Ainzen mandou buscar a Orihime.", new AuthorDTO(ulquiorra));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Partiu invadir Karakura", "Tô indo atrás daquele shinigami substituto", new AuthorDTO(grimmjow));

		CommentDTO c1 = new CommentDTO(null, "Que bom que foi. Tava com preguiça.", new AuthorDTO(stark));
		CommentDTO c2 = new CommentDTO(null, "Babá do numero 10 kkkkkkk", new AuthorDTO(grimmjow));
		
		CommentDTO c3 = new CommentDTO(null, "Apanhou legal em.", new AuthorDTO(ulquiorra));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepo.saveAll(Arrays.asList(post1, post2));
		
		grimmjow.getPosts().add(post2);
		userRepo.save(grimmjow);
		
		ulquiorra.getPosts().add(post1);
		userRepo.save(ulquiorra);
	}

}
