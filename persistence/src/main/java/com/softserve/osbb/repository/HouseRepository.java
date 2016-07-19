package com.softserve.osbb.repository;

import com.softserve.osbb.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {

    List<House> findByCity(String city);
    List<House> findByStreet(String street);


}
