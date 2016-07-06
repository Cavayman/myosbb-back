package com.softserve.osbb;

import com.softserve.osbb.dao.HouseRepository;
import com.softserve.osbb.model.HouseEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OsbbApplicationRunner.class)
@Transactional
public class HouseEntityDAOTest {

    private static final String TEST_ADDRESS_NAME = "Horodocka str. 110";

    @Autowired
    HouseRepository houseRepository;


    private HouseEntity house;

    @Before
    public void init(){
        house = new HouseEntity();
        house.setAdress(TEST_ADDRESS_NAME);
    }


    @Test
    public void testSaveAndDelete() {

        house = houseRepository.save(house);

        assertNotNull(house);

        assertNotEquals(0, (long) house.getHouseId());

        assertEquals(TEST_ADDRESS_NAME, house.getAdress());

        boolean houseExistsVal = houseRepository.exists(house.getHouseId());

        assertTrue(houseExistsVal);

        houseRepository.delete(house);

        assertFalse(houseRepository.exists(house.getHouseId()));


    }


    @Test
    public void testFindAll(){

        HouseEntity [] houses = {new HouseEntity("1"),
                new HouseEntity("2"),
                new HouseEntity("3"),
                new HouseEntity("4"),
                new HouseEntity("5")
                };

        houseRepository.save(Arrays.asList(houses));

        assertTrue(houseRepository.findAll().size() == 5);

    }

}


