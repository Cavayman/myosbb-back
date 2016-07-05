package com.softserve.osbb;

import com.softserve.osbb.dao.ProviderDAO;
import com.softserve.osbb.model.ProviderEntity;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import java.util.TreeSet;

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
    void init() {
        providerEntity = new ProviderEntity();
        providerEntity.setName("Garbage collector");
        providerEntity.setDescription("Remove trash");
        providerEntity.setLogourl("empty-logo");
    }

    @Autowired
    ProviderDAO providerDAO;


    @Test
    public void testSave() {
        Assert.assertNotNull(providerDAO.save(providerEntity));
        TreeSet<ProviderEntity> providers = new TreeSet<>();
        providers.add(providerEntity);
        providers.add(new ProviderEntity("A"));
        providers.add(new ProviderEntity("C"));
        providers.add(new ProviderEntity("B"));
        providerDAO.flush();
        Assert.assertNotNull(providerDAO.saveAndFlush(providerEntity));
        Assert.assertNotNull(providerDAO.save(providers));
    }

    @Test(dependsOnMethods = {"testSave"})
    public void testCount() {
        Assert.assertTrue(providerDAO.count()>3);
    }

    @Test(dependsOnMethods = {"testCount"})
    public void testFindAndDelete() {
        Integer providerID = providerDAO.save(providerEntity).getProviderId();
        Assert.assertTrue(providerDAO.exists(providerID));
        Assert.assertNotNull(providerDAO.findOne(providerID));
        Assert.assertNotNull(providerDAO.getOne(providerID));
        List<ProviderEntity> providers = providerDAO.findAll();
        Assert.assertNotNull(providers);
        providerDAO.delete(providerID);
        Assert.assertFalse(providerDAO.exists(providerID));
        providerDAO.delete(providers);
        for (ProviderEntity p: providers) {
            Assert.assertFalse(providerDAO.exists(p.getProviderId()));
        }
        providerDAO.deleteAll();
        Assert.assertTrue(providerDAO.count()==0);
    }

}
