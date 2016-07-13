package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.*;
import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.model.Staff;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
 * Created by Anastasiia Fedorak on 06.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
@Transactional
public class StaffRepositoryTest extends AbstractTestNGSpringContextTests {
    private Staff staff;
    
    @Autowired
    StaffRepository staffRepository;
    
    @Before
    public void init() {
        staff = new Staff();
    }
    
    @Test
    public void testSaveMethods(){
        Assert.assertNotNull(staffRepository.save(staff));
        Assert.assertTrue(staffRepository.count()==1);
    }
    
    @Test
    public void testFindAndDelete() {
        Integer providerID = staffRepository.save(staff).getStaffId();
        Assert.assertTrue(staffRepository.exists(providerID));
        Assert.assertNotNull(staffRepository.findOne(providerID));
        Assert.assertNotNull(staffRepository.getOne(providerID));
        List<Staff> staffs = staffRepository.findAll();
        Assert.assertNotNull(staffs);
        staffRepository.delete(providerID);
        Assert.assertFalse(staffRepository.exists(providerID));
        staffRepository.delete(staffs);
        for (Staff s: staffs) {
            Assert.assertFalse(staffRepository.exists(s.getStaffId()));
        }
        staffRepository.deleteAll();
        Assert.assertTrue(staffRepository.count()==0);
    }
}

