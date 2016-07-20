package com.softserve.osbb.repository;

import com.softserve.osbb.model.ProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Anastasiia Fedorak on 7/20/16.
 */

@Repository
public interface ProviderTypeRepository extends JpaRepository<ProviderType, Integer>{
}
