package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Message;
import com.softserve.osbb.repository.MessageRepository;
import com.softserve.osbb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by Kris on 10.07.2016.
 */
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message findOne(Integer id) {
        return messageRepository.findOne(id);
    }

    @Override
    public Message findOne(String id) {
        try {
            return messageRepository.findOne(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean exists(Integer id) {
        return messageRepository.exists(id);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> findAll(Sort sort) {
        return messageRepository.findAll(sort);
    }

    @Override
    public List<Message> findAll(Iterable<Integer> iterable) {
        return messageRepository.findAll(iterable);
    }

    @Override
    public long count() {
        return messageRepository.count();
    }

    @Override
    public void delete(Integer integer) {
        if (exists(integer)) {
            messageRepository.delete(integer);
        }
    }

    @Override
    public void delete(Message message) {
        if (exists(message.getMessageId()))
            messageRepository.delete(message.getMessageId());
    }

    @Override
    public void delete(Iterable<? extends Message> iterable) {
        messageRepository.delete(iterable);
    }

    @Override
    public void deleteAll() {
        messageRepository.deleteAll();
    }

    @Override
    public void flush() {
        messageRepository.flush();
    }

    @Override
    public Message getOne(Integer integer) {
        return messageRepository.getOne(integer);
    }

    @Override
    public Message saveAndFlush(Message message) {
        return messageRepository.saveAndFlush(message);
    }

    @Override
    public List<Message> save(Iterable<Message> iterable) {
        return messageRepository.save(iterable);
    }

    @Override
    public boolean update(Message message) {

        if (!messageRepository.exists(message.getMessageId())) {
            return false;
        }
        messageRepository.save(message);
        return true;

    }
}
