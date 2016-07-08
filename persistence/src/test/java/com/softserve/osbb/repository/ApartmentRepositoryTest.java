package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.Apartment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Oleg on 05.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
@Rollback
@Transactional
public class ApartmentRepositoryTest {
public static final Integer APPNUMBER = 111;
private Apartment apartment = new Apartment();
    @Autowired
    ApartmentRepository apartmentRepository;

    @Test
    public void testSave(){
        apartment.setNumber(APPNUMBER);
        apartmentRepository.save(apartment);
        Assert.assertNotNull(apartment);
        Assert.assertEquals(APPNUMBER, apartment.getNumber());
        apartmentRepository.delete(apartment.getApartmentId());
        Assert.assertFalse(apartmentRepository.exists(apartment.getApartmentId()));
    }

}
