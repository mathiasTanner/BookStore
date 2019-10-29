package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.haagahelia.course.BookStoreApplication;
import com.haagahelia.course.domain.Category;
import com.haagahelia.course.domain.CategoryRepository;



@RunWith(SpringRunner.class)
@ContextConfiguration (classes = BookStoreApplication.class)
@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository catRepo;

	@Test
    public void createNewCategory() {
    	Category category = new Category("testCat");
    	catRepo.save(category);
    	assertThat(category.getId()).isNotNull();
    }
	
	@Test
    public void deleteCategory() {
		
    	Category category= new Category("Dark");
    	catRepo.save(category);
    	
    	
    	catRepo.deleteById(category.getId());
    	
    	
    	assertThat(catRepo.findById(category.getId())).isEmpty();
    }
	
	@Test
    public void findByNameShouldReturnCategory() {
        List<Category> categories = catRepo.findByName("History");
        
        assertThat(categories).hasSize(1);
        assertEquals("History", categories.get(0).getName());
    }
}
