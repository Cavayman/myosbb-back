package com.softserve.osbb;
/*
import com.softserve.osbb.dao.MessageDAO;
import com.softserve.osbb.model.MassegeEntity;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Kris on 03.07.2016.
 */
/*
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OsbbApplicationRunner.class)
@Rollback
@Transactional
public class MessageDAOTest {

    @Autowired
    private MessageDAO messageEntityDAO;

    private MassegeEntity messageEntity = new MassegeEntity();


    @Test
    public void testMessageEntityOperations() {
        messageEntity.setTime("");
        messageEntity.setDescription("my description");
        messageEntity = messageEntityDAO.save(messageEntity);

        assertNotNull(messageEntity);
        assertTrue(messageEntity.getMassageId() != 0);

        int size = messageEntityDAO.findAll().size();
        assertTrue(size == 1);

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testSaveNullMessageEntity(){
        messageEntity = null;
        messageEntityDAO.save(messageEntity);

    }


}
*/
