package com.softserve.osbb.repository;

import com.softserve.osbb.model.Contract;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Roma on 06/07/2016.
 */
@Repository
public interface ContractRepository extends JpaRepository <Contract, Integer>, JpaSpecificationExecutor<Contract> {

    @Override
    List<Contract> findAll(Specification specification);

    @Override
    long count(Specification<Contract> specification);

    @Query("Select r From Contract r where r.creationDate >= ?1 and r.creationDate <= ?2")
    List<Contract> getAllContractsBetweenDates(LocalDateTime from, LocalDateTime to);

    @Query("Select r From Contract r where LOWER(r.name) LIKE LOWER(CONCAT('%',:searchParam,'%'))" +
            " OR LOWER(r.description) LIKE LOWER(CONCAT('%',:searchParam,'%'))")
    List<Contract> getAllContractsBySearchParam(@Param("searchParam") String searchTerm);

}
