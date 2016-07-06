package com.softserve.osbb.dao;

import com.softserve.osbb.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
}
