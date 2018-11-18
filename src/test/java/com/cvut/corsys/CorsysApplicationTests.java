package com.cvut.corsys;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cvut.corsys.dao.UserDao;
import com.cvut.corsys.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CorsysApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	public void contextLoads() {
		User user = new User();
		user.setUsername("zaboslav");
		user.setFirstName("Petr");
		user.setLastName("Bazik");
		user.setEmail("neco@nekde.cz");
		user.setPassword("heslo");
		this.userDao.save(user);
		System.out.println(this.userDao.findAll());
	}

}
