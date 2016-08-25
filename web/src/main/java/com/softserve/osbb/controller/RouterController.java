package com.softserve.osbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nazar.dovhyy on 24.08.2016.
 */
@Controller
public class RouterController {


    @RequestMapping({"/", "/myosbb", "/myosbb/login", "/myosbb/home/**"})
    public String index() {
        return "forward:/index.html";
    }

}
