package com.softserve.osbb.repository;


import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

        Apartment findByUser(User owner);
}
