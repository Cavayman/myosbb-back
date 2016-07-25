package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.Provider;
import com.softserve.osbb.model.enums.Periodicity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.TreeSet;


/**
 * Created by Anastasiia Fedorak on 05.07.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
@Rollback
@Transactional
public class ProviderRepositoryTest {
    private Provider provider;


    @Before
    public void init() {
        provider = new Provider();
        provider.setPeriodicity(Periodicity.ONE_TIME);
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

    @Test
    public void testCount() {
        TreeSet<Provider> providers = new TreeSet<>();
        providers.add(provider);
        providers.add(new Provider("A"));
        providers.add(new Provider("C"));
        providers.add(new Provider("B"));
        providerRepository.save(providers);
        Assert.assertTrue(providerRepository.count()>3);
    }

    @Test
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

    @Test
    public void testFindProvidersByNameOrDescription(){
        Assert.assertFalse(providerRepository.findProvidersByNameOrDescription("Garbage").isEmpty());
    }

    @Test
    public void testSaveProviderWrithEnumField(){
        provider.setPeriodicity(Periodicity.ONE_TIME);
        providerRepository.save(provider);
    }

}
