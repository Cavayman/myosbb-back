package com.softserve.osbb.service;

import com.softserve.osbb.model.House;

import java.util.List;

/**
 * Created by nazar.dovhyy on 14.07.2016.
 */
public interface HouseService {

    House addHouse(House house) throws Exception;
    House updateHouse(Integer houseId, House house) throws Exception;
    House findHouseById(Integer houseId) throws Exception;
    List<House> getAllHouses() throws Exception;
    List<House> findAllHousesBySearchParam(String searchParam) throws Exception;
    void deleteHouseById(Integer id) throws Exception;
    void deleteAllHouses() throws Exception;

}
