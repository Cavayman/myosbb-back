package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Role;

import com.softserve.osbb.repository.RoleRepository;
import com.softserve.osbb.service.RoleService;
import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roma on 13/07/2016.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findOne(Integer integer) {
        return roleRepository.findOne(integer);
    }

    @Override
    public Role findOne(String id) {
        return roleRepository.findOne(Integer.parseInt(id));
    }

    @Override
    public boolean exists(Integer integer) {
        return roleRepository.exists(integer);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findAll(Sort sort) {
        return roleRepository.findAll(sort);
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public List<Role> findAll(Iterable<Integer> iterable) {
        return roleRepository.findAll(iterable);
    }

    @Override
    public long count() {
        return roleRepository.count();
    }

    @Override
    public void delete(Integer integer) {
        roleRepository.delete(integer);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void delete(Iterable<? extends Role> iterable) {
        roleRepository.delete(iterable);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }

    @Override
    public void flush() {
        roleRepository.flush();
    }

    @Override
    public void deleteInBatch(Iterable<Role> iterable) {
        roleRepository.deleteInBatch(iterable);
    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Role getOne(Integer integer) {
        return roleRepository.getOne(integer);
    }

    @Override
    public Role saveAndFlush(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public List<Role> save(Iterable<Role> iterable) {
        return roleRepository.save(iterable);
    }



}
