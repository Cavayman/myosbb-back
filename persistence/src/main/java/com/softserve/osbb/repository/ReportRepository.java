package com.softserve.osbb.repository;


import com.softserve.osbb.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query("Select r From Report r where r.creationDate BETWEEN ?1 and ?2")
    List<Report> getAllReportsBetweenDates(LocalDate from, LocalDate to);

    @Query("Select r From Report r where LOWER(r.name) LIKE LOWER(CONCAT('%',:searchParam,'%'))" +
            " OR LOWER(r.description) LIKE LOWER(CONCAT('%',:searchParam,'%'))")
    List<Report> getAllReportsBySearchParam(@Param("searchParam") String searchTerm);

    List<Report> findByCreationDate(LocalDate localDate);

    List<Report> findDistinctByName(String name);

    long countByName(String name);

    @Query("select distinct r.creationDate from Report r")
    List<LocalDate> findDistinctCreationDates();

}
