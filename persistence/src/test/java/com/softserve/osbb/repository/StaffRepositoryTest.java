package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.model.Role;
import com.softserve.osbb.model.Staff;
import com.softserve.osbb.model.Staff;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

/**
 * Created by Aska on 06.07.2016.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
@Transactional
public class StaffRepositoryTest extends AbstractTestNGSpringContextTests {

    @Spy
    Staff staffEntity;

    @Autowired
    @InjectMocks
    private StaffRepository staffRepository;

    @BeforeClass
    public void strubbMock() {
        initMocks(this);
        Answer<String> osbb = new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                return "osbb №1";
            }
        };
        Answer<String> role = new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                return "admin";
            }
        };

        doNothing().when(staffEntity).setRole(any());
        when(staffEntity.getRole()).then(role);

        when(staffEntity.getStaffId()).thenCallRealMethod();
        doCallRealMethod().when(staffEntity).setStaffId(anyInt());
    }

    @Captor
    ArgumentCaptor<Role> roleEntity;

    @Captor
    ArgumentCaptor<Osbb> osbbEntity;

//    @org.testng.annotations.Test
//    public void testSaveMethods() {
//        initMocks(this);
//        staffRepository.deleteAll();
//        staffRepository.saveAndFlush(staffEntity);
//    }

}

