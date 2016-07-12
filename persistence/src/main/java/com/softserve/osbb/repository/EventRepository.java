package com.softserve.osbb.repository;

import com.softserve.osbb.model.Event;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, Integer>, JpaSpecificationExecutor<Event> {

    @Override
    List<Event> findAll(Specification specification);

    @Override
    long count(Specification<Event> specification);

    @Query("Select e From Event e where e.date >= ?1 and e.date <= ?2")
    List<Event> getAllEventsBetweenDates(LocalDateTime from, LocalDateTime to);

    @Query("Select e From Event e where LOWER(e.name) LIKE LOWER(CONCAT('%',:searchParam,'%'))" +
            " OR LOWER(e.description) LIKE LOWER(CONCAT('%',:searchParam,'%'))")
    List<Event> getAllEventsBySearchParam(@Param("searchParam") String searchTerm);
}
