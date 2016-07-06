package com.softserve.osbb.repository;

import com.softserve.osbb.model.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, Integer> {
}
