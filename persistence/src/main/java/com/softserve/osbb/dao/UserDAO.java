package com.softserve.osbb.dao;

import com.softserve.osbb.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cavayman on 05.07.2016.
 */
@Repository
public interface UserDAO extends JpaRepository<UserEntity,Integer> {
}
