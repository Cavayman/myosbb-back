package com.softserve.osbb.repository;


import com.softserve.osbb.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

    Apartment findByOwner(Integer owner);//do not delete this!!!
}
