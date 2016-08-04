package com.softserve.osbb.controller;

import com.softserve.osbb.model.Role;
import com.softserve.osbb.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Roma on 13/07/2016.
 */
@RestController
@CrossOrigin
@RequestMapping("/restful")
public class RoleController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;


    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public ResponseEntity<Resource<Role>> createRole(@RequestBody Role role) {
        logger.info("Create role.  " + role);
        role = roleService.addRole(role);
        return new ResponseEntity<>(addResourceLinkToRole(role), HttpStatus.OK);
    }


    @RequestMapping(value = "/role/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Role>> getRoleById(@PathVariable("id") Integer roleId) {
        logger.info("Get one role by id: " + roleId);
        Role role = roleService.getRole(roleId);
        return new ResponseEntity<>(addResourceLinkToRole(role), HttpStatus.OK);
    }

    @RequestMapping(value = "/role/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Role>> getRoleByName(@PathVariable("name") String name) {
        logger.info("Get one role by name: " + name);
        Role role = roleService.getRole(name);
        return new ResponseEntity<>(addResourceLinkToRole(role), HttpStatus.OK);
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<Role>>> getAllRole() {
        logger.info("Get all role: ");
        List<Role> roleList = roleService.getAllRole();
        final List<Resource<Role>> resourceRoleList = new ArrayList<>();

        for(Role o: roleList) {
            resourceRoleList.add(addResourceLinkToRole(o));
        }
        return new ResponseEntity<>(resourceRoleList, HttpStatus.OK);
    }

    @RequestMapping(value = "/role", method = RequestMethod.PUT)
    public ResponseEntity<Resource<Role>> updateRole(@RequestBody Role role) {
        logger.info("Update role with id: " + role.getRoleId());
        Role updatedRole = roleService.updateRole(role);
        return new ResponseEntity<>(addResourceLinkToRole(updatedRole), HttpStatus.OK);
    }

    @RequestMapping(value = "/role/id/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Resource<Role>> deleteRole(@PathVariable("id") Integer id) {
        logger.info("Delete role with id: " + id );
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/role", method = RequestMethod.DELETE)
    public ResponseEntity<Role>  deleteAllRole() {
        logger.info("Delete all role.");
        roleService.deleteAllRole();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Resource<Role> addResourceLinkToRole(Role role) {
        if (role == null) return null;
        Resource<Role> roleResource = new Resource<>(role);
        roleResource.add(linkTo(methodOn(RoleController.class)
                .getRoleById(role.getRoleId()))
                .withSelfRel());
        return roleResource;
    }



}
