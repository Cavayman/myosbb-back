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
import java.util.HashSet;

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
        provider = new Provider("Provider Name");
        provider.setPeriodicity(Periodicity.ONE_TIME);
        provider.setDescription("Garbage");
        provider.setLogoUrl("empty-logo");
    }

    @Autowired
    ProviderRepository providerRepository;

    @Test
    public void testSave() {
        Assert.assertNotNull(providerRepository.save(provider));
        HashSet<Provider> providers = new HashSet<>();
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
        HashSet<Provider> providers = new HashSet<>();
        providers.add(provider);
        providers.add(new Provider("A"));
        providers.add(new Provider("C"));
        providers.add(new Provider("B"));
        providerRepository.save(providers);
        Assert.assertTrue(providerRepository.count()>3);
    }

    @Test
    public void testFindProvidersByNameOrDescription(){
        providerRepository.save(new Provider("Name"));
        Assert.assertFalse(providerRepository.findProvidersByNameOrDescription("Name").isEmpty());
    }

    @Test
    public void testSaveProviderWrithEnumField(){
        provider.setPeriodicity(Periodicity.ONE_TIME);
        providerRepository.save(provider);
    }

}
