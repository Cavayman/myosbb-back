package com.softserve.osbb.repository;


import com.softserve.osbb.model.Report;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query("Select r From Report r where r.creationDate >= ?1 and r.creationDate <= ?2")
    List<Report> gerAllReportsBetweenDates(LocalDate from, LocalDate to);

    @Query("Select r From Report r where LOWER(r.name) LIKE LOWER(CONCAT('%',:searchParam,'%'))" +
            " OR LOWER(r.description) LIKE LOWER(CONCAT('%',:searchParam,'%'))")
    List<Report> getAllReportsBySearchParam(@Param("searchParam") String searchTerm);

    List<Report> findByCreationDate(LocalDate localDate);

}
