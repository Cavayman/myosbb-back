package com.softserve.osbb.service;

import com.softserve.osbb.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by cavayman on 11.07.2016.
 */
public interface UserService  {

    public User save(User user) ;

    public User findOne(Integer integer) ;

    public User findOne(String id) ;

    public boolean exists(Integer integer) ;

    public List<User> findAll() ;

    public List<User> findAll(Sort sort) ;

    public Page<User> findAll(Pageable pageable) ;

    public List<User> findAll(Iterable<Integer> iterable) ;

    public long count() ;

    public void delete(Integer integer) ;

    public void delete(User user) ;

    public void delete(Iterable<? extends User> iterable) ;

    public void deleteAll() ;

    public void flush() ;

    public void deleteInBatch(Iterable<User> iterable) ;

    public void deleteAllInBatch() ;

    public User getOne(Integer integer);

    public User saveAndFlush(User user) ;

    public List<User> save(Iterable<User> iterable) ;
}
