package com.softserve.osbb.repository;

import com.softserve.osbb.model.Osbb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OsbbRepository extends JpaRepository<Osbb, Integer> {

   /* @Query("SELECT o FROM osbb o where o.name = :name")
    public Osbb getByName(@Param("name") String name);*/

    public Osbb getByName(String name);
}