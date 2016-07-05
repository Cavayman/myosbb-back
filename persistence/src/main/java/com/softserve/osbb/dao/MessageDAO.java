package com.softserve.osbb.dao;

import com.softserve.osbb.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageDAO extends JpaRepository<MessageEntity, Integer> {
}
