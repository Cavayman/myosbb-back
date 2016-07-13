package com.softserve.osbb.controller;

import com.softserve.osbb.model.Role;
import com.softserve.osbb.service.RoleService;
import com.softserve.osbb.service.impl.RoleServiceImpl;
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
public class RoleController {
    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public List<Resource<Role>> getAll() {

        logger.info("Getting all rolles from database");
        List<Role> roles = roleService.findAll();
        logger.info("Passed RoleService");
        List<Resource<Role>> resources = new ArrayList<Resource<Role>>();
        for (Role temp : roles) {
            resources.add(getRoleResource(temp));
        }
        return resources;

    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public Resource<Role> getRole(@PathVariable(value = "id") String id) {
        logger.info("getting role from database with id=" + id);
       Role role = roleService.findOne(id);
        return getRoleResource(role);
    }


    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public Role putRole(@RequestBody Role role) {
        logger.info("Saving role, sending to service");
        return roleService.save(role);
    }

    @RequestMapping(value="/role/{id}",method=RequestMethod.POST)
    public Role updateRole(@PathVariable Integer id, @RequestBody Role role)
    {logger.info("Updating role id:"+id);
        role.setRoleId(id);
        return roleService.saveAndFlush(role);
    }

    @RequestMapping(value="/role",method=RequestMethod.DELETE)
    public boolean deleteAllRole(@RequestBody Role role)
    {logger.warn("Deleting all Roles");
        roleService.deleteAll();
        return true;
    }
    @RequestMapping(value="/role/{id}",method=RequestMethod.DELETE)
    public boolean deleteRoleByID(@PathVariable(value ="id") Integer id)
    {logger.warn("Deleting role with id:"+id);
        roleService.delete(id);
        return true;
    }

    private Resource<Role> getRoleResource(Role role) {
        Resource<Role> resource = new Resource<Role>(role);
        resource.add(linkTo(methodOn(RoleController.class).getRole(role.getRoleId().toString())).withSelfRel());
        return resource;
    }



}
