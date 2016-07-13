package com.softserve.osbb.controller;

import com.softserve.osbb.model.Osbb;
import com.softserve.osbb.service.OsbbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Roman on 12.07.2016.
 */
@RestController
@RequestMapping("/restful")
public class OsbbController {

    private static Logger logger = LoggerFactory.getLogger(OsbbController.class);

    @Autowired
    private OsbbService osbbService;

    @RequestMapping(value = "/osbb/add", method = RequestMethod.POST)
    public Osbb addOsbb(@RequestBody Osbb osbb) {
        logger.info("Add osbb" + osbb);
        return osbbService.addOsbb(osbb);
    }

    @RequestMapping(value = "/osbb/id={id}", method = RequestMethod.GET)
    public Osbb getOsbb(@PathVariable("id") Integer osbbId) {
        Osbb osbb = osbbService.getOsbb(osbbId);
        logger.info("Get one osbb by id: " + osbb);
        return osbb;
    }

    @RequestMapping(value = "/osbb/name={name}", method = RequestMethod.GET)
    public Osbb getOsbb(@PathVariable("name") String name) {
        logger.info("Get one osbb by name: " + name);
        return osbbService.getOsbb(name);
    }

    @RequestMapping(value = "/osbb/all", method = RequestMethod.GET)
    public List<Osbb> getAllOsbb() {
        List<Osbb> list = osbbService.getAllOsbb();
        logger.info("Get all osbb: " + list);
        return list;
    }

    @RequestMapping(value = "/osbb/id={id}", method = RequestMethod.PUT)
    public Osbb updateOsbb(@PathVariable("id") Integer id,
                           @RequestBody Osbb osbb) {
        System.out.println("TRY *****************************************");
        logger.info("Update osbb with id: " + id );
        return osbbService.updateOsbb(id, osbb);
    }

    @RequestMapping(value = "/osbb/id={id}", method = RequestMethod.DELETE)
    public void deleteOsbb(@PathVariable("id") Integer id) {
        logger.info("Delete osbb with id: " + id );
        osbbService.deleteOsbb(id);
    }

    @RequestMapping(value = "/osbb/all", method = RequestMethod.DELETE)
    public void deleteAll() {
        logger.info("Delete all osbb.");
        osbbService.deleteAllOsbb();
    }

}
