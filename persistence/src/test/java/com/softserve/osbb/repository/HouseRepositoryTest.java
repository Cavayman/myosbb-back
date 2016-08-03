package com.softserve.osbb.repository;

import com.softserve.osbb.PersistenceConfiguration;
import com.softserve.osbb.model.House;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistenceConfiguration.class)
@Transactional
public class HouseRepositoryTest {

    private static final String TEST_ADDRESS_NAME = "Horodocka str. 110";
    private static final String TEST_CITY_NAME = "Lviv";
    private static final String TEST_ZIP_CODE = "79040";
    private static final int TEST_SEARCH_ID = 9999;

    @Autowired
    HouseRepository houseRepository;
    @Autowired
    ApartmentRepository apartmentRepository;

    private House house;


    @Before
    public void beforeMethod(){
        house = new House();
        house.setStreet(TEST_ADDRESS_NAME);
        house.setCity(TEST_CITY_NAME);
        house.setZipCode(TEST_ZIP_CODE);
    }


    @Test
    public void testSaveHouse() {

        House savedHouse = houseRepository.save(house);
        assertNotNull(house);
        assertTrue(savedHouse.getCity()==TEST_CITY_NAME);
        assertTrue(savedHouse.getStreet()==TEST_ADDRESS_NAME);
        assertTrue(savedHouse.getZipCode()==TEST_ZIP_CODE);
        assertEquals(TEST_ADDRESS_NAME, house.getStreet());
        boolean houseExistsVal = houseRepository.exists(savedHouse.getHouseId());
        assertTrue(houseExistsVal);
    }

    @Test
    public void testDeleteHouseThatExists(){

        House savedHouse = houseRepository.save(house);
        assertTrue(houseRepository.exists(savedHouse.getHouseId()));
        houseRepository.delete(savedHouse);
        assertFalse(houseRepository.exists(house.getHouseId()));

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDeleteHouseThatNotExists(){
        houseRepository.delete(TEST_SEARCH_ID);
    }

    @Test
    public void testUpdateHouseThatExists(){
        House savedHouse = houseRepository.save(house);
        savedHouse.setCity("London");
        House updatedHouse =houseRepository.save(savedHouse);
        assertEquals(savedHouse, updatedHouse);
    }



    @Test
    public void testFindAll(){

        House[] houses = {new House("1"),
                new House("2"),
                new House("3"),
                new House("4"),
                new House("5")
        };

        List<House> houseList = houseRepository.save(Arrays.asList(houses));

        assertTrue(houseList.size()==5);

    }

    @Test
    public void testGetAllAppartmentsByHouseid(){

        /*
        Integer houseId = Integer.valueOf(9);
        House house = houseRepository.findOne(houseId);
        System.out.println(house.getApartments());
        */

       // houseRepository.delete(9);

    }

}