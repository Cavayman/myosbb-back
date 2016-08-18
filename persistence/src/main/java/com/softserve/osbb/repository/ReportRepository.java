package com.softserve.osbb.repository;


import com.softserve.osbb.model.Report;
import com.softserve.osbb.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("Select r From Report r where r.user=?1 AND r.creationDate BETWEEN ?2 and ?3")
    List<Report> getAllUserReportsBetweenDates(User user, LocalDate from, LocalDate to);

    @Query("Select r From Report r where LOWER(r.name) LIKE LOWER(CONCAT('%',:searchParam,'%'))" +
            " OR LOWER(r.description) LIKE LOWER(CONCAT('%',:searchParam,'%'))")
    List<Report> getAllReportsBySearchParam(@Param("searchParam") String searchTerm);

    List<Report> findByUser(User user);

    List<Report> findByCreationDate(LocalDate localDate);

    List<Report> findDistinctByName(String name);

    long countByName(String name);

    @Query("select distinct r.creationDate from Report r")
    List<LocalDate> findDistinctCreationDates();

    Page<Report> findByUser(User user, Pageable pageable);

}
