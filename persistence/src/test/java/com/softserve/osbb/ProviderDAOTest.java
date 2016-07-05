package com.softserve.osbb;

import com.softserve.osbb.dao.ProviderDAO;
import com.softserve.osbb.model.ProviderEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

/**
 * Created by Aska on 05.07.2016.
 */
public class ProviderDAOTest {
    private ProviderEntity providerEntity = new ProviderEntity();

    @Autowired
    ProviderDAO providerDAO;

    @Test
    public void testFindAll(){

        Assert.assertNotNull(providerEntity.getProviderId());

    }
}
