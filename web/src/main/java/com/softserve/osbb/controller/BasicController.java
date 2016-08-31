package com.softserve.osbb.controller;

import com.softserve.osbb.model.User;
import com.softserve.osbb.service.UserService;
import com.softserve.osbb.service.impl.MailSenderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by cavayman on 03.08.2016.
 */

@CrossOrigin
@RestController
public class BasicController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final List<Resource<User>> EMPTY_LIST = new ArrayList<>(0);

    @Autowired
    UserService userService;
    @Autowired
    OsbbService osbbService;
    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    public String login(@RequestBody Map<String, String> json) throws ServletException {
        if (json.get("username") == null || json.get("password") == null) {
            throw new ServletException("Please fill in username and password");
        }
        String userName = json.get("username");
        String password = json.get("password");

        User user = userService.findUserByEmail(userName);
        if (user == null) {
            throw new ServletException("User name not found");
        }
        String pwd = user.getPassword();
        if (!password.equals(pwd)) {
            throw new ServletException("Invalid.Please check you'r username and password");
        }
        return Jwts.builder().setId(user.getUserId().toString()).setSubject(userName).claim("roles", "User").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
    }
    */

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public User putUser(@RequestBody User user) {
        if(userService.findUserByEmail(user.getEmail())!=null)
            return userService.findUserByEmail(user.getEmail());
        logger.info("Saving user, sending to service");
        return userService.save(user);
    }

    @RequestMapping(value = "/validEmail", method = RequestMethod.POST)
    public HttpStatus validateEmail(@RequestBody String email) {
        if(userService.findUserByEmail(email)!=null) {
            System.out.println(userService.findUserByEmail(email));
            return HttpStatus.FOUND;
        }
        return HttpStatus.NOT_FOUND;
    }

    @Autowired
    private MailSenderImpl sender;

    @RequestMapping(value = "/sendEmailMail", method = RequestMethod.POST)
    public HttpStatus sendEmailOnMail(@RequestBody String email) throws MessagingException {
        User user=userService.findUserByEmail(email);
        if(user!=null) {
            sender.send(email,"My-osbb.Your forgoten password","Hello there friend here is your pass:"+user.getPassword());
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.NOT_FOUND;
    }
}
