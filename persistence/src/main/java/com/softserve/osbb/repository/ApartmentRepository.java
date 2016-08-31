package com.softserve.osbb.repository;


import com.softserve.osbb.model.Apartment;

import com.softserve.osbb.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {
    Page<Apartment> findByNumber(Integer number, Pageable pageable);

    Apartment findByOwner(Integer owner);//do not delete this!!!
}
