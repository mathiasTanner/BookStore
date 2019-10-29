package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.haagahelia.course.BookStoreApplication;
import com.haagahelia.course.domain.User;
import com.haagahelia.course.domain.UserRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration (classes = BookStoreApplication.class)
@DataJpaTest
public class UserRepositoryTest {
	@Autowired
	UserRepository userRepo;
	
	@Test
    public void createNewUser() {
    	User user = new User("user2", "$2a$10$pcyvJXKC3KyOBx988OOBB.Zx6qTNCWJoUNWW7rAJQPpOWPz0mo/n2", "USER");
    	userRepo.save(user);
    	assertThat(user.getId()).isNotNull();
    }
	
	@Test
    public void deleteUser() {
		
		User user = new User("user3", "$2a$10$pcyvJXKC3KyOBx988OOBB.Zx6qTNCWJoUNWW7rAJQPpOWPz0mo/n2", "USER");
		userRepo.save(user);
    	
    	
		userRepo.deleteById(user.getId());
    	
    	
    	assertThat(userRepo.findById(user.getId())).isEmpty();
    }
	

}
