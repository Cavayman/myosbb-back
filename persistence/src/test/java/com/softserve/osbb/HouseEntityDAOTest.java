package com.softserve.osbb;

import com.softserve.osbb.dao.HouseDAO;
import com.softserve.osbb.model.HouseEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OsbbApplicationRunner.class)
@Transactional
public class HouseEntityDAOTest {

    private static final String TEST_ADDRESS_NAME = "Horodocka str. 110";


    @Autowired
    HouseDAO houseDAO;


    private HouseEntity house;

    @Before
    public void init(){
        house = new HouseEntity();
        house.setAdress(TEST_ADDRESS_NAME);
    }


    @Test
    public void testSaveAndDelete() {

        house = houseDAO.save(house);

        assertNotNull(house);

        assertNotEquals(0, (long) house.getHouseId());

        assertEquals(TEST_ADDRESS_NAME, house.getAdress());

        boolean houseExistsVal = houseDAO.exists(house.getHouseId());

        assertTrue(houseExistsVal);

        houseDAO.delete(house);

        assertFalse(houseDAO.exists(house.getHouseId()));

    }


    @Test()
    public void testFindAll(){

        HouseEntity [] houses = {new HouseEntity("1"), new HouseEntity("2"), new HouseEntity("3")};

        for(HouseEntity house: houses){
            houseDAO.save(house);
        }

        assertTrue(houseDAO.findAll().size() == 3);

    }

}


