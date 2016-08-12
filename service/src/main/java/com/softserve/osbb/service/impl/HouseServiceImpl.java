package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.House;
import com.softserve.osbb.repository.HouseRepository;
import com.softserve.osbb.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazar.dovhyy on 19.07.2016.
 */
@Service
@Transactional
public class HouseServiceImpl implements HouseService {

    private static final List<House> EMPTY_HOUSE_LIST = new ArrayList<>(0);
    private static final List<Apartment> EMPTY_APPARTMENT_LIST = new ArrayList<>(0);
    private static final int DEF_ROWS = 10;
    @Autowired
    private HouseRepository houseRepository;

    @Override
    public House addHouse(House house) {
        return house == null ? House.EMPTY_HOUSE : addHouseIfNotNull(house);
    }

    private House addHouseIfNotNull(House house) {
        house = houseRepository.save(house);
        return house;
    }

    @Override
    public House updateHouse(Integer houseId, House house) {
        return house == null ? House.EMPTY_HOUSE : updateHouseIfExists(house);
    }

    private House updateHouseIfExists(House house) {
        final boolean houseExists = houseRepository.equals(house);
        if (!houseExists) {
            return House.EMPTY_HOUSE;
        }
        return house;
    }

    @Override
    public House findHouseById(Integer houseId) {
        return houseRepository.exists(houseId) ? houseRepository.findOne(houseId) : House.EMPTY_HOUSE;
    }

    @Override
    public List<House> getAllHousesBySearchParameter(String searchTerm) {
        return (searchTerm == null || searchTerm.isEmpty()) ?
                EMPTY_HOUSE_LIST : houseRepository.getAlReportsBySearchParameter(searchTerm);
    }

    @Override
    public List<House> findAllByCity(String city) {
        return (city == null || city.isEmpty()) ?
                EMPTY_HOUSE_LIST : houseRepository.findByCity(city);
    }

    @Override
    public List<House> findAllByStreet(String street) {
        return (street == null || street.isEmpty()) ?
                EMPTY_HOUSE_LIST : houseRepository.findByStreet(street);
    }


    @Override
    public List<Apartment> findAllAppartmentsByHouseId(Integer houseId) {
        House house = findHouseById(houseId);
        return house != null ? (List<Apartment>) house.getApartments() : EMPTY_APPARTMENT_LIST;
    }

    @Override
    public boolean deleteHouseById(Integer id) {
        final boolean houseExists = houseRepository.exists(id);
        if (!houseExists) {
            return false;
        }
        houseRepository.delete(id);
        return true;

    }

    @Override
    public void deleteAllHouses() {
        houseRepository.deleteAll();
    }

    @Override
    public Page<House> getAllHouses(Integer pageNumber, String sortedBy, Boolean order, Integer rowNum) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, rowNum == null ? DEF_ROWS : rowNum,
                getSortingOrder(order), sortedBy == null ? "street" : sortedBy);
        return houseRepository.findAll(pageRequest);
    }

    private Sort.Direction getSortingOrder(Boolean order) {
        if (order == null) {
            return Sort.Direction.DESC;
        }
        return order == true ? Sort.Direction.ASC : Sort.Direction.DESC;
    }


}
