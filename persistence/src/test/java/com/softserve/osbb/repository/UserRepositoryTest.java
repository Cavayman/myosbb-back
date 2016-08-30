package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.Role;
import com.softserve.osbb.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cavayman on 05.07.2016.
 */
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
public class UserRepositoryTest extends Assert {
    private User user;
    private User user2;
    private Role role1;
    private Role role2;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleUserRepository;

    @Before
    public void setUpToUserObject() {
        role1 = new Role();
        role1.setName("ROLE_TEST1");
        role2 = new Role();
        role2.setName("ROLE_TEST2");

        user = new User();
        user.setFirstName("Oleg");
        user.setLastName("Kotsik");
        user.setEmail("cavaymanTESTforTEST@gmail.com");
        user.setPassword("1111");
        user.setGender("JuniorJavaDev");
        user.setPhoneNumber("+380679167305");
        user.setBirthDate(new java.util.Date(System.currentTimeMillis()));

        user2 = new User();
        user2.setFirstName("Oleg");
        user2.setLastName("Kotsik");
        user2.setEmail("fuckthisemail@gmail.com");
        user2.setPassword("1111");
        user2.setGender("JuniorJavaDev");
        user2.setPhoneNumber("+380679167305");
        user2.setBirthDate(new java.util.Date(System.currentTimeMillis()));

    }


    @Test
    public void saveTest() {
        userRepository.save(user);
        userRepository.save(user2);
        assertEquals(user, userRepository.findUserByEmail(user.getEmail()));
        assertEquals(user2, userRepository.findUserByEmail(user2.getEmail()));

    }

    @Test
    public void findByEmailTest() {
        userRepository.save(user);
        userRepository.save(user2);
        assertEquals(user.getEmail(), userRepository.findUserByEmail(user.getEmail()).getEmail());
        assertEquals(user2.getEmail(), userRepository.findUserByEmail(user2.getEmail()).getEmail());
        assertNotEquals(user.getEmail(), userRepository.findUserByEmail(user2.getEmail()).getEmail());
    }

    @Test
    public void findByRoleUser() {

        user.setRole(role1);
        user2.setRole(role1);
        userRepository.save(user);
        userRepository.save(user2);

        assertTrue(userRepository.findByRole(role1).size() >= 2);

        user.setRole(role2);
        user2.setRole(role2);
        userRepository.save(user);
        userRepository.save(user2);
        assertTrue(userRepository.findByRole(role2).size() >= 2);

        assertEquals(user.getRole().getName(),roleUserRepository.findByUsers(user).getName());
        assertTrue(userRepository.findAll().size() >= 2);
    }


    @After public void afterTest(){
    userRepository.deleteAll();
    }
}
