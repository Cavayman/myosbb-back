package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.BarChartData;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Override
    public BarChartData getBarChartData(Integer year) {
        List<Bill> bills = billRepository.getAllByYear(year);
        BarChartData barChartData = new BarChartData();
        logger.info("fetching list of months");
        List<String> months = bills.stream()
                .map((b) -> b.getDate().getMonth().toString())
                .distinct()
                .collect(Collectors.toList());
        logger.info("fetching list of months: " + months);
        logger.info("fetching list of years");
        List<Integer> years = bills.stream()
                .map((b) -> b.getDate().getYear())
                .distinct()
                .collect(Collectors.toList());
        logger.info("fetching list of years: " + years);
        double totalPaid;
        double totalUnPaid;
        for (String month : months) {
            totalPaid = 0;
            totalUnPaid = 0;
            for (Bill bill : bills) {
                if (month.equalsIgnoreCase(bill.getDate().getMonth().toString())
                        && bill.getBillStatus() == BillStatus.PAID) {
                    totalPaid += bill.getPaid();

                } else if (month.equalsIgnoreCase(bill.getDate().getMonth().toString())
                        && bill.getBillStatus() == BillStatus.NOT_PAID) {
                    totalUnPaid += bill.getToPay();
                } else {
                    //
                }
            }
            logger.info("totalPaid: " + totalPaid);
            logger.info("totalUnPaid: " + totalUnPaid);
            barChartData.getInnerBarChart().add(new BarChartData.InnerBarChart(month, totalPaid, totalUnPaid));
        }
        barChartData.setYears(years);

        return barChartData;
    }
}
