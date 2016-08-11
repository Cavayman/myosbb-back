package com.softserve.osbb.service;

import com.softserve.osbb.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roma on 13/07/2016.
 */

@Service
public interface RoleService {

    Role addRole(Role role);

    Role getRole(Integer id);

    Role getRole(String name);

    List<Role> getAllRole();

    long countRole();

    boolean existsRole(Integer id);

    Role updateRole(Role role);

    void deleteRole(Integer id);

    void deleteRole(Role role);

    void deleteAllRole();

    Page<Role> getAllRole(Integer pageNumber, String sortedBy, Boolean ascOrder);
}
