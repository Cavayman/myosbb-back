package com.softserve.osbb.repository;

import com.softserve.osbb.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nazar.dovhyy on 04.07.2016.
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
}
