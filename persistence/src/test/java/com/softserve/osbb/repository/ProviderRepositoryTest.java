package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.Provider;
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
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
@Rollback
@Transactional
public class ProviderRepositoryTest extends AbstractTestNGSpringContextTests {
    private Provider provider;


    @BeforeTest
    public void init() {
        provider = new Provider();
        provider.setName("Garbage collector");
        provider.setDescription("Remove trash");
        provider.setLogoUrl("empty-logo");
    }

    @Autowired
    ProviderRepository providerRepository;


    @Test
    public void testSave() {
        Assert.assertNotNull(providerRepository.save(provider));
        TreeSet<Provider> providers = new TreeSet<>();
        providers.add(provider);
        providers.add(new Provider("A"));
        providers.add(new Provider("C"));
        providers.add(new Provider("B"));
        providerRepository.flush();
        Assert.assertNotNull(providerRepository.saveAndFlush(provider));
        Assert.assertNotNull(providerRepository.save(providers));
    }

    @Test(dependsOnMethods = {"testSave"})
    public void testCount() {
        Assert.assertTrue(providerRepository.count()>3);
    }

    @Test(dependsOnMethods = {"testCount"})
    public void testFindAndDelete() {
        Integer providerID = providerRepository.save(provider).getProviderId();
        Assert.assertTrue(providerRepository.exists(providerID));
        Assert.assertNotNull(providerRepository.findOne(providerID));
        Assert.assertNotNull(providerRepository.getOne(providerID));
        List<Provider> providers = providerRepository.findAll();
        Assert.assertNotNull(providers);
        providerRepository.delete(providerID);
        Assert.assertFalse(providerRepository.exists(providerID));
        providerRepository.delete(providers);
        for (Provider p: providers) {
            Assert.assertFalse(providerRepository.exists(p.getProviderId()));
        }
        providerRepository.deleteAll();
        Assert.assertTrue(providerRepository.count()==0);
    }

}
