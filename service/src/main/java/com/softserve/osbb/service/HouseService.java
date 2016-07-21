package com.softserve.osbb.service;

import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.House;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nazar.dovhyy on 14.07.2016.
 */

@Service
public interface HouseService {

    House addHouse(House house) throws Exception;
    House updateHouse(Integer houseId, House house) throws Exception;
    House findHouseById(Integer houseId) throws Exception;
    List<House> getAllHouses() throws Exception;
    List<House> findAllByCity(String city) throws Exception;
    List<House> findAllByStreet(String street);
    List<Apartment> findAllAppartmentsByHouseId(Integer houseId);
    boolean deleteHouseById(Integer id) throws Exception;
    void deleteAllHouses() throws Exception;

}
