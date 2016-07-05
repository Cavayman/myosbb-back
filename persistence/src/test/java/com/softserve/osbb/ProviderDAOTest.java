package com.softserve.osbb;

import com.softserve.osbb.dao.ProviderDAO;
import com.softserve.osbb.model.ProviderEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

/**
 * Created by Aska on 05.07.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OsbbApplicationRunner.class)
@Rollback
@Transactional
public class ProviderDAOTest extends AbstractTestNGSpringContextTests {
    private ProviderEntity providerEntity;


    @BeforeTest
    void init(){
        providerEntity = new ProviderEntity();
        providerEntity.setName("Garbage collector");
        providerEntity.setDescription("Remove trash");
        providerEntity.setLogourl("empty-logo");
    }

    @Autowired
    ProviderDAO providerDAO;


    @Test
    public void testSave(){
        System.out.println(providerDAO.save(providerEntity));
    }

    @Test
    public void testFindAll(){
        Assert.assertNotNull(providerEntity.getProviderId());

    }
}
