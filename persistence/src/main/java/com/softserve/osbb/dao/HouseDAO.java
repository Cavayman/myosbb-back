package com.softserve.osbb.dao;

import com.softserve.osbb.model.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HouseDAO extends JpaRepository<HouseEntity, Integer> {
}
