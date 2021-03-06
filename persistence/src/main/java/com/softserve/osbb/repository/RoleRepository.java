package com.softserve.osbb.repository;

import com.softserve.osbb.model.Role;
import com.softserve.osbb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by nazar.dovhyy on 04.07.2016.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    @Query("select r from Role r where r.name = :name")
    public Role findByName(@Param("name") String name);

    public Role findByUsers(User users);

}
