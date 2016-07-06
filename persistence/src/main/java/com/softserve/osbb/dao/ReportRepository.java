package com.softserve.osbb.dao;


import com.softserve.osbb.model.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {
}
