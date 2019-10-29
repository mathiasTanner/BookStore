package com.haagahelia.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Year;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haagahelia.course.domain.Book;
import com.haagahelia.course.domain.BookRepository;
import com.haagahelia.course.domain.Category;
import com.haagahelia.course.domain.CategoryRepository;
import com.haagahelia.course.domain.User;
import com.haagahelia.course.domain.UserRepository;

@SpringBootApplication
public class BookStoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookInit(BookRepository bookRepo, CategoryRepository catRepo, UserRepository userRepo) {
		return (args) -> {
			log.info("populate the db with catogries");
			catRepo.save(new Category("Comedy"));
			catRepo.save(new Category("History"));
			
			log.info("populate the db with books");
			bookRepo.save(new Book("TheTitle","TheAuth",Year.parse("2012"),"ISBN-001",20.5, catRepo.findByName("Comedy").get(0)));
			bookRepo.save(new Book("TheTitle2","TheAuth2",Year.parse("2016"),"ISBN-002",33.9, catRepo.findByName("History").get(0)));
			
			User user1 = new User("user", "$2a$10$pcyvJXKC3KyOBx988OOBB.Zx6qTNCWJoUNWW7rAJQPpOWPz0mo/n2", "USER");
			User user2 = new User("admin", "$2a$10$qzjN5i5Azvoq8abOZRbVeu63FKFG3uKKjYLNlu7VM5NFWrW1oIAjK", "ADMIN");
			userRepo.save(user1);
			userRepo.save(user2);
			
			log.info("All Books:");
			for (Book book : bookRepo.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
