package com.softserve.osbb.repository;


import com.softserve.osbb.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
}
