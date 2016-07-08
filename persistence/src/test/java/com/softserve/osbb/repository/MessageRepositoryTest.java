package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.Message;
import com.softserve.osbb.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * Created by Kris on 05.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
@Transactional
public class MessageRepositoryTest {

    private Message message = new Message();

    private User user = new User();
    @Autowired
    MessageRepository messageRepository;

    @Test
    public void testFindAll(){
        Assert.assertNull(message.getMessageId());
        user.setBirthdate(new Date());
        user.setEmail("blabla@gmail.com");
        user.setFirstname("Vanya");
        user.setLastname("Popov");
        user.setGender("man");

        message.setUsers(user);
        message.setMessage("Hi! This is a first message in our database.");
        message.setDescription("some description");
        message.setTime("some date");

        messageRepository.save(message);
        Assert.assertNotNull(message.getMessageId());

    }
}
