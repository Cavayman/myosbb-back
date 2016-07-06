package com.softserve.osbb.dao;

import com.softserve.osbb.model.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Roma on 06/07/2016.
 */
@Repository
public interface ContractRepository extends JpaRepository <ContractEntity, Integer> {

}
