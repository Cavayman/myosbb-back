package com.softserve.osbb.repository;

import com.softserve.osbb.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("Select event From Event event where LOWER(event.name) LIKE LOWER(CONCAT('%',:search,'%'))" +
            " or LOWER(event.author) LIKE LOWER(CONCAT('%',:search,'%'))" +
            " or LOWER(event.description) LIKE LOWER(CONCAT('%',:search,'%'))")
    List<Event> findByNameOrAuthorOrDescription(@Param("search") String search);
}
