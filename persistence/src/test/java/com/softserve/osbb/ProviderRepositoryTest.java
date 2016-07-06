package com.softserve.osbb;

import com.softserve.osbb.dao.ProviderRepository;
import com.softserve.osbb.model.ProviderEntity;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
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
public class ProviderRepositoryTest extends AbstractTestNGSpringContextTests {
    private ProviderEntity providerEntity;


    @Before
    public void init() {
        providerEntity = new ProviderEntity();
        providerEntity.setName("Garbage collector");
        providerEntity.setDescription("Remove trash");
        providerEntity.setLogourl("empty-logo");
    }

    @Autowired
    ProviderRepository providerRepository;


    @org.junit.Test
    public void testSave() {
        Assert.assertNotNull(providerRepository.save(providerEntity));
        TreeSet<ProviderEntity> providers = new TreeSet<>();
        providers.add(providerEntity);
        providers.add(new ProviderEntity("A"));
        providers.add(new ProviderEntity("C"));
        providers.add(new ProviderEntity("B"));
        providerRepository.flush();
        Assert.assertNotNull(providerRepository.saveAndFlush(providerEntity));
        Assert.assertNotNull(providerRepository.save(providers));
    }

    @Test(dependsOnMethods = {"testSave"})
    public void testCount() {
        Assert.assertTrue(providerRepository.count()>3);
    }

    @Test(dependsOnMethods = {"testCount"})
    public void testFindAndDelete() {
        Integer providerID = providerRepository.save(providerEntity).getProviderId();
        Assert.assertTrue(providerRepository.exists(providerID));
        Assert.assertNotNull(providerRepository.findOne(providerID));
        Assert.assertNotNull(providerRepository.getOne(providerID));
        List<ProviderEntity> providers = providerRepository.findAll();
        Assert.assertNotNull(providers);
        providerRepository.delete(providerID);
        Assert.assertFalse(providerRepository.exists(providerID));
        providerRepository.delete(providers);
        for (ProviderEntity p: providers) {
            Assert.assertFalse(providerRepository.exists(p.getProviderId()));
        }
        providerRepository.deleteAll();
        Assert.assertTrue(providerRepository.count()==0);
    }

}
