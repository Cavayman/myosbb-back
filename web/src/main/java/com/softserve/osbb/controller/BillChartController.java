package com.softserve.osbb.controller;

import com.softserve.osbb.model.BarChartData;
import com.softserve.osbb.model.BillChartData;
import com.softserve.osbb.service.BillChartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nazar.dovhyy on 28.08.2016.
 */
@RestController
@CrossOrigin
@RequestMapping("restful/chart")
public class BillChartController {
    private static final Logger logger = LoggerFactory.getLogger(BillChartController.class);

    @Autowired
    private BillChartService billChartService;


    @RequestMapping(value="/percentage", method = RequestMethod.GET)
    public ResponseEntity<BillChartData> getBillChartData() {
        logger.info("fetching chart data");
        BillChartData billChartData = billChartService.getBillChartData();
        return new ResponseEntity<>(billChartData, HttpStatus.OK);
    }

    @RequestMapping(value="/{year}/bar", method = RequestMethod.GET)
    public ResponseEntity<BarChartData> getBarChartData(@PathVariable("year") Integer year) {
        logger.info("fetching bar chart data by year "+year);
        BarChartData barChartData = billChartService.getBarChartData(year);
        logger.info("received: "+barChartData);
        return new ResponseEntity<>(barChartData, HttpStatus.OK);
    }
}
