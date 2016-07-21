package com.softserve.osbb.repository;

import com.softserve.osbb.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Anastasiia Fedorak on 05.07.2016.
 * Assigned to Anastasiia on 20.07.2016
 */

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer>{

    @Query("Select provider From Provider provider where LOWER(provider.name) LIKE LOWER(CONCAT('%',:search,'%'))" +
            " or LOWER(provider.description) LIKE LOWER(CONCAT('%',:search,'%'))")
    List<Provider> findProvidersByNameOrDescription(@Param("search") String search);
}

