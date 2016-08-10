package com.softserve.osbb.service; /**
 * Created by cavayman on 11.07.2016.
 */

import com.softserve.osbb.config.ServiceApplication;
import com.softserve.osbb.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;


/**
 * Created by cavayman on 05.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
public class UserServiceTest {
    private User user;
    @Autowired
    private UserService userService;

    @Before
    public void setUpToUserObject() {
        user=new User();
        user.setFirstName("Oleg");
        user.setLastName("Kotsik");
        user.setEmail("cavayman@gmail.com");
        user.setPassword("1111");
        user.setGender("JuniorJavaDev");
        user.setPhoneNumber("+380679167305");
        user.setBirthDate(new java.util.Date(System.currentTimeMillis()));
    }



    @Test
    public void testSave() {
        userService.save(user);
        assertEquals(user.getUserId(), userService.findOne(user.getUserId()).getUserId());

    }
    @After
    public void afterTest(){
        userService.delete(user);
    }
}
