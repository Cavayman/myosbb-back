package com.softserve.osbb.service.gen;

import com.softserve.osbb.model.Bill;
import com.softserve.osbb.model.User;
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


    public JRDataSource getInvoiceModelList() {
        logger.info("fetching all bills from the database");
        List<Bill> bills = billRepository.findAll();
        List<InvoiceModel> invoiceModelList = new ArrayList<>();
        bills.forEach((bill) -> addToInvoiceModelList(convertFrom(bill), invoiceModelList));
        JRBeanCollectionDataSource invoiceModelListDataSource = new JRBeanCollectionDataSource(invoiceModelList, false);
        return invoiceModelListDataSource;
    }

    public JRDataSource getCurrentUserInvoiceModelList(User currentUser) {
        if (currentUser == null) {
            throw new IllegalArgumentException("currentUser is null");
        }
        logger.info("fetching all bills from the database for current user ", currentUser.getUserId());
        List<Bill> bills = billRepository.findAllByUserId(currentUser.getUserId());
        List<InvoiceModel> invoiceModelList = new ArrayList<>();
        bills.forEach((bill) -> addToInvoiceModelList(convertFrom(bill), invoiceModelList));
        JRBeanCollectionDataSource invoiceModelListDataSource = new JRBeanCollectionDataSource(invoiceModelList, false);
        return invoiceModelListDataSource;
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

    private void addToInvoiceModelList(InvoiceModel invoiceModel, List<InvoiceModel> invoiceModels) {
        logger.info("adding report to list for " + invoiceModel.getCustomerName());
        invoiceModels.add(invoiceModel);
    }

}
