package com.softserve.osbb.controller;

import com.softserve.osbb.config.JwtFilter;
import com.softserve.osbb.model.User;
import com.softserve.osbb.service.UserService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by cavayman on 12.07.2016.
 */
@RestController
@RequestMapping(value = "restful/")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final List<Resource<User>> EMPTY_LIST = new ArrayList<>(0);
    @Autowired
    UserService userService;


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<Resource<User>>> getAll() {
        logger.info("Getting all users from database");
        List<User> users = userService.findAll();
        List<Resource<User>> resources = new ArrayList<Resource<User>>();

        if (users.isEmpty()) {
            return new ResponseEntity<>(EMPTY_LIST,HttpStatus.OK);
        } else {
            for (User temp : users) {
                resources.add(getUserResource(temp));
            }
            return new ResponseEntity<>(resources, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/user/getCurrent", method = RequestMethod.GET)
    public Resource<User> getCurrent(final HttpServletRequest request) {
        final Claims claims = (Claims) request.getAttribute("claims");
           String id= claims.getId();
       return  getUser(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Resource<User> getUser(@PathVariable(value = "id") String id) {
        logger.info("geting user from database with id=" + id);
        User user = userService.findOne(id);
        return getUserResource(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User putUser(@RequestBody User user) {
        logger.info("Saving user, sending to service");
        return userService.save(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        logger.info("Updating user id:" + id);
        user.setUserId(id);
        return userService.saveAndFlush(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public boolean deleteAllUsers(@RequestBody User user) {
        logger.warn("Deleting all Users");
        userService.deleteAll();
        return true;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public boolean deleteUserByID(@PathVariable(value = "id") Integer id) {
        logger.warn("Deleting user with id:" + id);
        userService.delete(id);
        return true;
    }


    private Resource<User> getUserResource(User user) {
        Resource<User> resource = new Resource<>(user);
        resource.add(linkTo(methodOn(UserController.class).getUser(user.getUserId().toString())).withSelfRel());
//        resource.add(linkTo(methodOn(ApartmentController.class).getAppartmentByOwner(user.getUserId())).withRel("apartments "));
        return resource;

    }

}
