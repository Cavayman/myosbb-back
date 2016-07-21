package com.softserve.osbb.repository;

import com.softserve.osbb.model.ProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Anastasiia Fedorak on 7/20/16.
 */

@Repository
public interface ProviderTypeRepository extends JpaRepository<ProviderType, Integer>{

    @Query("Select p From ProviderType p where LOWER(p.providerTypeName) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<ProviderType> findProviderTypesByName(@Param("name") String name);
}
