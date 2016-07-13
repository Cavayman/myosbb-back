package com.softserve.osbb.repository;

import com.softserve.osbb.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Aska on 05.07.2016.
 */

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer>{

    @Query("Select provider From Provider provider where LOWER(provider.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Provider> findProvidersByName(@Param("name") String name);
}

