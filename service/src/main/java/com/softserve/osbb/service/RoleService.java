package com.softserve.osbb.service;

import com.softserve.osbb.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roma on 13/07/2016.
 */

@Service
public interface RoleService {

    public Role save(Role role);

    public Role findOne(Integer integer);

    public Role findOne (String id);

    public boolean exists (Integer integer);

    public List<Role> findAll();

    public List<Role> findAll(Sort sort) ;

    public Page<Role> findAll(Pageable pageable) ;

    public List<Role> findAll(Iterable<Integer> iterable) ;

    public long count() ;

    public void delete(Integer integer) ;

    public void delete(Role role) ;

    public void delete(Iterable<? extends Role> iterable) ;

    public void deleteAll();

    public void flush() ;

    public void deleteInBatch(Iterable<Role> iterable) ;

    public void deleteAllInBatch() ;

    public Role getOne(Integer integer);

    public Role saveAndFlush(Role role) ;

    public List<Role> save(Iterable<Role> iterable) ;

}
