package com.softserve.osbb.service;

import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nazar.dovhyy on 14.07.2016.
 */

@Service
public interface HouseService {

    House addHouse(House house);

    House updateHouse(Integer houseId, House house);

    House findHouseById(Integer houseId);

    List<House> getAllHousesBySearchParameter(String searchTerm);

    List<House> findAllByCity(String city);

    List<House> findAllByStreet(String street);

    List<House> findAll();

    List<Apartment> findAllApartmentsByHouseId(Integer houseId);

    boolean deleteHouseById(Integer id);

    void deleteAllHouses();

    Page<House> getAllHouses(Pageable pageable);
}
