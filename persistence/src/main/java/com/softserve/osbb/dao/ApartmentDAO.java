package com.softserve.osbb.dao;


import com.softserve.osbb.model.AppartamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApartmentDAO extends JpaRepository<AppartamentEntity, Integer> {
}
