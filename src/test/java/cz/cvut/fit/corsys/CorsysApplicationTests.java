package cz.cvut.fit.corsys;

import cz.cvut.fit.corsys.dl.dao.RoleDao;
import cz.cvut.fit.corsys.dl.dao.UserDao;
import cz.cvut.fit.corsys.dl.entity.Role;
import cz.cvut.fit.corsys.dl.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public class CorsysApplicationTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Test
    public void contextLoads() {
        User user = new User();

        user.setUsername("zaboslav");
        user.setFirstName("Petr");
        user.setLastName("Bazik");
        user.setEmail("neco@nekde.cz");
        user.setPassword("X03MO1qnZdYdgyfeuILPmQ==");

        Role role = new Role();
        role.setRole("admin");
        user.setRole(role);
        roleDao.save(role);

        this.userDao.save(user);
        System.out.println(this.userDao.findAll());

    }

}
