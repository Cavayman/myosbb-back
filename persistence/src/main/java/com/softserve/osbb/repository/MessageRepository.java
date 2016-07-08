package com.softserve.osbb.repository;


import com.softserve.osbb.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Kris on 05.07.2016.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
