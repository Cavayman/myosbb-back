package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Bill;
import com.softserve.osbb.model.BillChartData;
import com.softserve.osbb.model.enums.BillStatus;
import com.softserve.osbb.repository.BillRepository;
import com.softserve.osbb.service.BillChartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nazar.dovhyy on 28.08.2016.
 */
@Service
public class BillChartServiceImpl implements BillChartService {

    private static Logger logger = LoggerFactory.getLogger(BillChartServiceImpl.class);

    @Autowired
    private BillRepository billRepository;

    @Override
    public BillChartData getBillChartData() {
        List<Bill> bills = billRepository.findAll();
        logger.info("fetch all bills " + bills);
        BillChartData billChartData = new BillChartData();
        long totalPaidBills = bills.stream()
                .filter((b) -> b.getBillStatus() == BillStatus.PAID)
                .count();
        logger.info("totalPaidBills " + totalPaidBills);
        long totalUnpaidBills = bills.stream()
                .filter((b) -> b.getBillStatus() == BillStatus.NOT_PAID)
                .count();
        logger.info("totalUnpaidBills " + totalUnpaidBills);
        double totalPaidBillsPercentage = Math.round(((double) totalPaidBills / (double) bills.size() * 100) * 100) / 100;
        double totalUnpaidBillsPercentage = Math.round(((double) totalUnpaidBills / (double) bills.size() * 100) * 100) / 100;
        logger.info("totalPaidBills % " + totalPaidBillsPercentage);
        logger.info("totalUnpaidBills % " + totalUnpaidBillsPercentage);
        billChartData.setTotalPercentagePaid(totalPaidBillsPercentage);
        billChartData.setTotalPercentageDebt(totalUnpaidBillsPercentage);
        logger.info("billChartData " + billChartData.toString());
        return billChartData;
    }
}
