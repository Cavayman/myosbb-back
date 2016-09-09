package com.softserve.osbb.repository;

import com.softserve.osbb.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("Select event From Event event where LOWER(event.title) LIKE LOWER(CONCAT('%',:search,'%'))" +
            " or LOWER(event.author) LIKE LOWER(CONCAT('%',:search,'%'))" +
            " or LOWER(event.description) LIKE LOWER(CONCAT('%',:search,'%'))")
    List<Event> findByNameOrAuthorOrDescription(@Param("search") String search);

    @Query("select event from Event event where event.startTime between :startTime and :endTime" +
            " or event.endTime between :startTime and :endTime")
    List<Event> findBetweenStartTimeAndEndTime(@Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime);
}
