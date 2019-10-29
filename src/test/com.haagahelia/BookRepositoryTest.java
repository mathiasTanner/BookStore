package com.haagahelia;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.w2ex4BookStore.domain.Book;
import com.example.w2ex4BookStore.domain.BookRepository;
import com.example.w2ex4BookStore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
    private BookRepository repository;
	@Autowired
	private CategoryRepository catRepository;

	@Test
    public void createNewBook() {
    	Book book = new Book("Martin3","Rowlings","2007","PQL-158",16.9,catRepository.findByName("Tragedy").get(0));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }
	
	@Test
    public void DeleteBook() {
		//We create a book that we will delete just after
    	Book book = new Book("Martin4","Rowlings","2008","PQL-159",16.9,catRepository.findByName("Tragedy").get(0));
    	repository.save(book);
    	
    	//We delete this book
    	repository.deleteById(book.getId());
    	
    	//We test that it is really deleted
    	assertThat(repository.findById(book.getId())).isEmpty();
    }
	
	
	
    @Test
    public void findByIsbnShouldReturnBook() {
        List<Book> books = repository.findByIsbn("PQL-156");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Martin1");
    }
    
}
