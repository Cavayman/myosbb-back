package com.softserve.osbb.service.gen;

import com.softserve.osbb.model.Bill;
import com.softserve.osbb.model.report.InvoiceModel;
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
public class InvoiceCreator {

    @Autowired
    private BillRepository billRepository;

    private static Logger logger = LoggerFactory.getLogger(InvoiceCreator.class);


    public JRDataSource getDataSource() {
        logger.info("fetching all bills from the database");
        List<Bill> bills = billRepository.findAll();
        List<InvoiceModel> reportDTOList = new ArrayList<>();
        bills.forEach((bill) -> addToReportDTOList(convertFrom(bill), reportDTOList));
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportDTOList, false);
        return jrBeanCollectionDataSource;
    }

    private InvoiceModel convertFrom(Bill bill) {
        logger.info("converting data to InvoiceModel");
        InvoiceModel invoiceModel =
                InvoiceModel.setBillId(bill.getBillId())
                        .setAmountToPay(bill.getToPay())
                        .setAmountPaid(bill.getPaid())
                        .setCustomerName(bill.getApartment().getUser())
                        .setCustomerEmail(bill.getApartment().getUser())
                        .setProviderDescription(bill.getProvider());

        return invoiceModel;
    }

    private void addToReportDTOList(InvoiceModel invoiceModel, List<InvoiceModel> invoiceModels) {
        logger.info("adding report to list for " + invoiceModel.getCustomerName());
        invoiceModels.add(invoiceModel);
    }

}
