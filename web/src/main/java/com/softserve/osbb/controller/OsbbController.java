package com.softserve.osbb.controller;

import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.service.OsbbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Roman on 12.07.2016.
 */
@RestController
@RequestMapping("/osbb")
public class OsbbController {

    @Autowired
    private OsbbService osbbService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Osbb getOsbbById(@PathVariable("id") Integer osbbId) {

        return osbbService.getOsbb(osbbId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Osbb> listAllOsbb() {

        return osbbService.getAllOsbb();
    }



}
