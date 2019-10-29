package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.time.Year;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.haagahelia.course.BookStoreApplication;
import com.haagahelia.course.domain.Book;
import com.haagahelia.course.domain.BookRepository;
import com.haagahelia.course.domain.CategoryRepository;



@RunWith(SpringRunner.class)
@ContextConfiguration (classes = BookStoreApplication.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
    private BookRepository bookRepo;
	@Autowired
	private CategoryRepository catRepo;

	@Test
    public void createNewBook() {
    	Book book = new Book("testBook","testAuth",Year.parse("2019"),"ISBN-003",16.9d,catRepo.findByName("History").get(0));
    	assertEquals(null, book.getId());
    	bookRepo.save(book);
    	assertThat(book.getId()).isNotNull();
    	
    }
	
	@Test
    public void DeleteBook() {
		//We create a book that we will delete just after
    	Book book = new Book("testBook2","testAuth2",Year.parse("2018"),"ISBN-004",16.9,catRepo.findByName("History").get(0));
    	bookRepo.save(book);
    	
    	//We delete this book
    	bookRepo.deleteById(book.getId());
    	
    	//We test that it is really deleted
    	assertThat(bookRepo.findById(book.getId())).isEmpty();
    }
	
	
	
    @Test
    public void findByIsbn() {
        List<Book> books = bookRepo.findByIsbn("ISBN-001");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("TheTitle");
        
    }
    
}
