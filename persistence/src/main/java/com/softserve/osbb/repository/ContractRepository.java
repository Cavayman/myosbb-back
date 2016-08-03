package com.softserve.osbb.repository;

import com.softserve.osbb.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by Roma on 06/07/2016.
 *
 */
@Repository
public interface ContractRepository extends JpaRepository <Contract, Integer>, JpaSpecificationExecutor<Contract>  {


}
