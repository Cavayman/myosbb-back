package com.softserve.osbb.dao;


import com.softserve.osbb.model.MassegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageDAO extends JpaRepository<MassegeEntity, Integer> {
}
