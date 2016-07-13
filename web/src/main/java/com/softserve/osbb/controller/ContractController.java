package com.softserve.osbb.controller;

import com.softserve.osbb.model.Contract;
import com.softserve.osbb.service.ContractService;
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
public class ContractController {
    private static Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    ContractService contractService;

    @RequestMapping(value = "/contract", method = RequestMethod.GET)
    public List<Resource<Contract>> getAll() {

        logger.info("Getting all contracts from database");
        List<Contract> contracts = contractService.findAll();
        logger.info("Passed ContractService");
        List<Resource<Contract>> resources = new ArrayList<Resource<Contract>>();
        for (Contract temp : contracts) {
            resources.add(getContractResource(temp));
        }
        return resources;

    }

    @RequestMapping(value = "/contract/{id}", method = RequestMethod.GET)
    public Resource<Contract> getContract(@PathVariable(value = "id") String id) {
        logger.info("getting contract from database with id=" + id);
        Contract contract = contractService.findOne(id);
        return getContractResource(contract);
    }


    @RequestMapping(value = "/contract", method = RequestMethod.POST)
    public Contract putContract(@RequestBody Contract contract) {
        logger.info("Saving contract, sending to service");
        return contractService.save(contract);
    }

    @RequestMapping(value="/contract/{id}",method=RequestMethod.POST)
    public Contract updateContract(@PathVariable Integer id, @RequestBody Contract contract)
    {logger.info("Updating contract id:"+id);
        contract.setContractId(id);
        return contractService.saveAndFlush(contract);
    }

    @RequestMapping(value="/contract",method=RequestMethod.DELETE)
    public boolean deleteAllContracts(@RequestBody Contract contract)
    {logger.warn("Deleting all Contracts");
        contractService.deleteAll();
        return true;
    }
    @RequestMapping(value="/contract/{id}",method=RequestMethod.DELETE)
    public boolean deleteContractByID(@PathVariable(value ="id") Integer id)
    {logger.warn("Deleting contract with id:"+id);
        contractService.delete(id);
        return true;
    }

    private Resource<Contract> getContractResource(Contract contract) {
        Resource<Contract> resource = new Resource<Contract>(contract);
        resource.add(linkTo(methodOn(ContractController.class).getContract(contract.getContractId().toString())).withSelfRel());
        return resource;
    }


}
