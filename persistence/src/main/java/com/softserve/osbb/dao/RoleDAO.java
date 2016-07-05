package com.softserve.osbb.dao;

import com.softserve.osbb.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nazar.dovhyy on 04.07.2016.
 */
@Repository
public interface RoleDAO extends JpaRepository<RoleEntity, Integer> {
}
