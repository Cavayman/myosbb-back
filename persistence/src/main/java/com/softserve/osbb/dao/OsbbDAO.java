package com.softserve.osbb.dao;

import com.softserve.osbb.model.OsbbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OsbbDAO extends JpaRepository<OsbbEntity, Integer> {
}