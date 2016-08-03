package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Role;
import com.softserve.osbb.repository.RoleRepository;
import com.softserve.osbb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Role addRole(Role role) {
        if(role == null) {
            return null;
        }
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public Role getRole(Integer id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Role getRole(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public long countRole() {
        return roleRepository.count();
    }

    @Override
    public boolean existsRole(Integer id) {
        return roleRepository.exists(id);
    }

    @Override
    public Role updateRole(Role role) {
        if(roleRepository.exists(role.getRoleId())) {
            return roleRepository.save(role);
        } else {
            throw new IllegalArgumentException("Role with id=" + role.getRoleId()
                    + " doesn't exist. First try to add this role.");
        }
    }

    @Override
    public void deleteRole(Integer id) {
        roleRepository.delete(id);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void deleteAllRole() {
        roleRepository.deleteAll();
    }
}
