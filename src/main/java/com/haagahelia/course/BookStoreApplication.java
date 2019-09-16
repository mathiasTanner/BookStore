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

@SpringBootApplication
public class BookStoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookInit(BookRepository repository, CategoryRepository catRepository) {
		return (args) -> {
			log.info("populate the db with catogries");
			catRepository.save(new Category("Comedy"));
			catRepository.save(new Category("History"));
			
			log.info("populate the db with books");
			repository.save(new Book("TheTitle","TheAuth",Year.parse("2012"),"ISBN-001",20.5, catRepository.findByName("Comedy").get(0)));
			repository.save(new Book("TheTitle2","TheAuth2",Year.parse("2016"),"ISBN-002",33.9, catRepository.findByName("History").get(0)));
			
			log.info("All Books:");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
