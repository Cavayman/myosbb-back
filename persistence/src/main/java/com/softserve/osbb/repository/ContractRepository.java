package com.softserve.osbb.repository;

import com.softserve.osbb.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Roma on 06/07/2016.
 *
 */
@Repository
public interface ContractRepository extends JpaRepository <Contract, Integer>, JpaSpecificationExecutor<Contract>  {

    @Query("Select contract From Contract contract where LOWER(contract.provider.name) LIKE LOWER(CONCAT('%',:search,'%'))")
            List<Contract> findContractsByProviderName(@Param("search") String search);

            Page<Contract> findByActiveTrue(Pageable pageable);
            List<Contract> findByActiveTrue();
}
