package com.softserve.osbb.dao;


import com.softserve.osbb.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Kris on 05.07.2016.
 */
@Repository
public interface MessageDAO extends JpaRepository<MessageEntity, Integer> {
}
