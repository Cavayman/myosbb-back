package com.softserve.osbb.service;

import com.softserve.osbb.model.Message;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by Kris on 12.07.2016.
 */
public interface MessageService {
    Message save(Message message);

    Message findOne(Integer id);

    Message findOne(String id);

    boolean exists(Integer id);

    List<Message> findAll();

    List<Message> findAll(Sort sort);

    List<Message> findAll(Iterable<Integer> iterable);

    long count();

    void delete(Integer integer);

    void delete(Message message);

    void delete(Iterable<? extends Message> iterable);

    void deleteAll();

    void flush();

    Message getOne(Integer id);

    Message saveAndFlush(Message message);

    List<Message> save(Iterable<Message> iterable);

    Message update(Message message);

}
