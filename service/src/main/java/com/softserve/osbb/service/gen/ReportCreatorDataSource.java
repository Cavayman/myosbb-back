package com.softserve.osbb.service.gen;

import com.softserve.osbb.model.Bill;
import com.softserve.osbb.model.report.BillReportDTO;
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
public class ReportCreatorDataSource {

    @Autowired
    private BillRepository billRepository;

    private static Logger logger = LoggerFactory.getLogger(ReportCreatorDataSource.class);


    public JRDataSource getDataSource() {
        logger.info("fetching all bills from the database");
        List<Bill> bills = billRepository.findAll();
        List<BillReportDTO> reportDTOList = new ArrayList<>();
        bills.forEach((bill) -> addToReportDTOList(convertFrom(bill), reportDTOList));
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportDTOList, false);
        return jrBeanCollectionDataSource;
    }

    private BillReportDTO convertFrom(Bill bill) {
        logger.info("converting data to BillReportDTO");
        BillReportDTO billReportDTO =
                BillReportDTO.setBillId(bill.getBillId())
                        .setAmountToPay(bill.getToPay())
                        .setAmountPaid(bill.getPaid())
                        .setApartmentNumber(bill.getApartment())
                        .setOwnerName(bill.getApartment().getUser())
                        .setProviderName(bill.getProvider());

        return billReportDTO;
    }

    private void addToReportDTOList(BillReportDTO billReportDTO, List<BillReportDTO> billReportDTOs) {
        logger.info("adding report to list for " + billReportDTO.getOwnerName());
        billReportDTOs.add(billReportDTO);
    }

}
