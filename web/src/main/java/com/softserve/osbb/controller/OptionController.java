//package com.softserve.osbb.controller;
//
//import com.softserve.osbb.model.Option;
//import com.softserve.osbb.service.OptionService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.Resource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
//
//
///**
// * Created by Roman on 28.07.2016.
// */
//@RestController
//@CrossOrigin
//@RequestMapping("/restful")
//public class OptionController {
//
//    private static Logger logger = LoggerFactory.getLogger(OptionController.class);
//
//    @Autowired
//    private OptionService optionService;
//
//    @RequestMapping(value = "/option", method = RequestMethod.POST)
//    public ResponseEntity<Resource<Option>> createOption(@RequestBody Option option) {
//        logger.info("Create option.  " + option);
//        option = optionService.addOption(option);
//        return new ResponseEntity<>(addResourceLinkToOption(option), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/option/id/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Resource<Option>> getOptionById(@PathVariable("id") Integer optionId) {
//        logger.info("Get one option by id: " + optionId);
//        Option option = optionService.getOption(optionId);
//        return new ResponseEntity<>(addResourceLinkToOption(option), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/option", method = RequestMethod.GET)
//    public ResponseEntity<List<Resource<Option>>> getAllOption() {
//        logger.info("Get all option.");
//        List<Option> optionList = optionService.getAllOption();
//        final List<Resource<Option>> resourceOptionList = new ArrayList<>();
//
//        for(Option o: optionList) {
//            resourceOptionList.add(addResourceLinkToOption(o));
//        }
//        return new ResponseEntity<>(resourceOptionList, HttpStatus.OK);
//    }
//
//
//    @RequestMapping(value = "/option", method = RequestMethod.PUT)
//    public ResponseEntity<Resource<Option>> updateOption(@RequestBody Option option) {
//        logger.info("Update option with id: " + option.getOptionId());
//        Option updatedOption = optionService.updateOption(option);
//        return new ResponseEntity<>(addResourceLinkToOption(updatedOption), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/option/id/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Resource<Option>> deleteOption(@PathVariable("id") Integer id) {
//        logger.info("Delete option with id: " + id );
//        optionService.deleteOption(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/option", method = RequestMethod.DELETE)
//    public ResponseEntity<Option>  deleteAllOption() {
//        logger.info("Delete all option.");
//        optionService.deleteAllOption();
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    private Resource<Option> addResourceLinkToOption(Option option) {
//        if (option == null) return null;
//        Resource<Option> optionResource = new Resource<>(option);
//        optionResource.add(linkTo(methodOn(OptionController.class)
//                .getOptionById(option.getOptionId()))
//                .withSelfRel());
//        return optionResource;
//    }
//
//}
