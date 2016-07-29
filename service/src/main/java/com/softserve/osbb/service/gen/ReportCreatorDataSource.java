package com.softserve.osbb.service.gen;

import com.softserve.osbb.model.Bill;
import com.softserve.osbb.model.User;
import com.softserve.osbb.model.report.BillReportDTO;
import com.softserve.osbb.repository.BillRepository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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


    public JRDataSource getDataSource() {

        List<Bill> bills = billRepository.findAll();
        List<BillReportDTO> reportDTOList = new ArrayList<>();
        bills.forEach((bill) -> addToReportDTOList(convertFrom(bill), reportDTOList));
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportDTOList);
        return jrBeanCollectionDataSource;
    }

    private BillReportDTO convertFrom(Bill bill) {
        BillReportDTO billReportDTO = new BillReportDTO();
        billReportDTO.setBillId(bill.getBillId());
        User owner = bill.getApartment().getUser();
        billReportDTO.setOwnerName(owner.getLastName() + " " + owner.getFirstName());
        billReportDTO.setApartmentNumber(bill.getApartment().getNumber());
        billReportDTO.setProviderName(bill.getProvider().getName());
        billReportDTO.setAmountToPay(bill.getToPay());
        billReportDTO.setAmountPaid(bill.getPaid());
        return billReportDTO;
    }

    private void addToReportDTOList(BillReportDTO billReportDTO, List<BillReportDTO> billReportDTOs) {
        billReportDTOs.add(billReportDTO);
    }

}
