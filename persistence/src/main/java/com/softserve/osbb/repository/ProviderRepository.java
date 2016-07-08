package com.softserve.osbb.repository;

import com.softserve.osbb.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Aska on 05.07.2016.
 */

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer>{

}
