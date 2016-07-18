package com.softserve.osbb.service;

import com.softserve.osbb.config.ServiceApplication;
import com.softserve.osbb.model.Provider;
import com.softserve.osbb.service.ProviderService;
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
 * Created by Anastasiia Fedorak on 13.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
@Rollback
@Transactional
public class ProviderServiceTest {
    private Provider provider;
    List<Provider> providers;

    @Autowired
    ProviderService providerService;

    @Before
    public void init() {
        provider = new Provider();
        provider.setName("Volya");
        provider.setDescription("internet provider");
        providers = new ArrayList<>();
        providers.add(provider);
    }

    @Test
    public void testSaveAndFind() {
        providerService.saveProvider(provider);
        Assert.assertNotNull(provider.getProviderId());
        providerService.saveProviderList(providers);
        Assert.assertNotNull(providerService.findOneProviderById(provider.getProviderId()));
        Assert.assertNotNull(providerService.findAllProviders());
    }

    @Test
    public void testDelete() {
        testSaveAndFind();
        providerService.deleteProvider(provider);
        Assert.assertNull(providerService.findOneProviderById(provider.getProviderId()));
        providerService.deleteAllProviders();
        Assert.assertTrue(providerService.findAllProviders().isEmpty());
    }

    @Test
    public void testCountAndExists() {
        testSaveAndFind();
        Assert.assertTrue(providerService.countProviders() >= 1);
        Assert.assertTrue(providerService.existsProvider(provider.getProviderId()));
    }

    @Test
    public void testFindProvidersByNameOrDescription(){
        testSaveAndFind();
        Assert.assertFalse(providerService.findProvidersByNameOrDescription("Volya").isEmpty());
    }
}