package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.User;
import com.softserve.osbb.repository.UserRepository;
import com.softserve.osbb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cavayman on 11.07.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findOne(Integer integer) {
        return userRepository.findOne(integer);
    }

    @Override
    public boolean exists(Integer integer) {
        return userRepository.exists(integer);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findAll(Iterable<Integer> iterable) {
        return userRepository.findAll(iterable);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void delete(Integer integer) {
        userRepository.delete(integer);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void delete(Iterable<? extends User> iterable) {
        userRepository.delete(iterable);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void flush() {
        userRepository.flush();
    }

    @Override
    public void deleteInBatch(Iterable<User> iterable) {
        userRepository.deleteInBatch(iterable);
    }

    @Override
    public void deleteAllInBatch() {
        userRepository.deleteAllInBatch();
    }

    @Override
    public User getOne(Integer integer) {
        return userRepository.getOne(integer);
    }

    @Override
    public User saveAndFlush(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> save(Iterable<User> iterable) {
        return userRepository.save(iterable);
    }
}
