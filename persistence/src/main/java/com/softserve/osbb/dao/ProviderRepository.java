package com.softserve.osbb.dao;

import com.softserve.osbb.model.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Aska on 05.07.2016.
 */

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Integer>{

}
