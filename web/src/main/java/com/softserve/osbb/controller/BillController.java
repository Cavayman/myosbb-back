package com.softserve.osbb.controller;

import com.softserve.osbb.model.Bill;
import com.softserve.osbb.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by nataliia on 11.07.16.
 */

@CrossOrigin
@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @RequestMapping(value = "/bills", method = RequestMethod.GET)
    public List<Bill> findAllBills() {
        return billService.findAllBills();
    }

    @RequestMapping(value = "/bill/{ids}", method = RequestMethod.GET)
    public List<Bill> findBill(List<Integer> ids) {
        return billService.findAllBillsByIDs(ids);
    }

    @RequestMapping(value = "/bill/{id}", method = RequestMethod.GET)
    public Bill findOneBill(Integer id) {
        return billService.findOneBillByID(id);
    }
}
