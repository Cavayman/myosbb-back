package com.softserve.osbb.repository;

import com.softserve.osbb.model.Osbb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Roman on 06.07.2016.
 */

@Repository
public interface OsbbRepository extends JpaRepository<Osbb, Integer> {

    @Query("select o from Osbb o where o.name = :name")
    public Osbb getByName(@Param("name") String name);

}