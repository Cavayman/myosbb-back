package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by cavayman on 05.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
public class UserRepositoryTest extends Assert {
    private User user;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUpToUserObject() {
       user=new User();
        user.setFirstname("Oleg");
        user.setLastname("Kotsik");
        user.setEmail("cavayman@gmail.com");
        user.setPassword("1111");
        user.setGender("JuniorJavaDev");
        user.setPhonenumber("+380679167305");
        user.setBirthdate(new java.util.Date(System.currentTimeMillis()));
    }



    @Test
    public void testToHexString() {
        assertEquals(0, userRepository.findAll().size());
        userRepository.save(user);
        assertEquals(1, userRepository.findAll().size());

    }
}
