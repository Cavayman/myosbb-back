package com.softserve.osbb.repository;

import com.softserve.osbb.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {

    List<House> findByCity(String city);

    List<House> findByStreet(String street);

    @Query("Select h From House h where LOWER(h.street) LIKE LOWER(CONCAT('%',?1,'%'))" +
            " OR LOWER(h.city) LIKE LOWER(CONCAT('%',?1,'%'))" +
            "OR LOWER(h.description) LIKE LOWER(CONCAT('%',?1,'%'))" +
            "OR LOWER(h.zipCode) LIKE LOWER(CONCAT('%',?1,'%'))")
    List<House> getAlReportsBySearchParameter(String searchTerm);


}
