package com.softserve.osbb.service.utils;

import com.softserve.osbb.model.Bill;
import com.softserve.osbb.model.User;
import com.softserve.osbb.model.report.ReportModel;
import com.softserve.osbb.repository.BillRepository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
@Service
public class ReportCreator {

    private static Logger logger = LoggerFactory.getLogger(ReportCreator.class);
    @Autowired
    private BillRepository billRepository;

    public JRDataSource getReportModelListDataSource() {
        logger.info("fetching all bills from the database");
        List<Bill> bills = billRepository.findAll();
        return createReportModelListDataSource(bills);
    }

    public JRDataSource getReportModelListDataSource(User currentUser) {
        if (currentUser == null) {
            throw new IllegalArgumentException("currentUser is null");
        }
        logger.info("fetching all bills from the database for current user ", currentUser.getUserId());
        List<Bill> bills = billRepository.findAllByUserId(currentUser.getUserId());
        return createReportModelListDataSource(bills);
    }

    private JRBeanCollectionDataSource createReportModelListDataSource(List<Bill> bills) {
        List<ReportModel> reportModelList = new ArrayList<>();
        bills.forEach((bill) -> addToReportModelList(convertFrom(bill), reportModelList));
        return new JRBeanCollectionDataSource(reportModelList, false);
    }


    private ReportModel convertFrom(Bill bill) {
        logger.info("converting data to ReportModel");
        ReportModel reportModel =
                ReportModel.setBillId(bill.getBillId())
                        .setAmountToPay(bill.getToPay())
                        .setAmountPaid(bill.getPaid())
                      //  .setCustomerName(bill.getApartment().getUser())
                       // .setCustomerEmail(bill.getApartment().getUser())
                        .setProviderDescription(bill.getProvider());

        return reportModel;
    }

    private void addToReportModelList(ReportModel reportModel, List<ReportModel> reportModels) {
        logger.info("adding report to list for " + reportModel.getCustomerName());
        reportModels.add(reportModel);
    }

}
