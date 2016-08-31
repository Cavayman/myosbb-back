package com.softserve.osbb.dto.mappers;

import com.softserve.osbb.dto.BillDTO;
import com.softserve.osbb.model.Bill;
import com.softserve.osbb.model.enums.BillStatus;
import com.softserve.osbb.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nazar.dovhyy on 18.08.2016.
 */
public class BillDTOMapper {

    private static Logger logger = LoggerFactory.getLogger(BillDTOMapper.class);

    public static BillDTO mapEntityToDTO(Bill bill) {
        BillDTO billDTO = null;
        if (bill != null) {
            billDTO = new BillDTO.BillDTOBuilder()
                    .setBillId(bill.getBillId())
                    .setDate(bill.getDate())
                    .setApartmentNumber(bill.getApartment())
                    .setPaid(bill.getPaid())
                    .setToPay(bill.getToPay())
                    .setTariff(bill.getTariff())
                    .setDescription(bill.getProvider())
                    .setStatus(bill.getBillStatus())
                    .setApartmentId(bill.getApartment())
                    .setProviderId(bill.getProvider())
                    .build();
        }
        return billDTO;
    }

    public static Bill mapDTOtoEntity(BillDTO billDTO, BillService billService) {
        Bill bill = null;
        if (billDTO != null) {
            if (billDTO.getBillId() == null) {
                bill = new Bill();
                createNewBillFromBillDTO(billDTO, bill);
                return bill;
            }
            if (billService == null) {
                throw new IllegalArgumentException("no instance of billService provided");
            }
            bill = billService.findOneBillByID(billDTO.getBillId());
            mapFromBillDTOTOBill(billDTO, bill);

        }
        return bill;
    }

    private static void createNewBillFromBillDTO(BillDTO billDTO, Bill bill) {
        bill.setTariff(billDTO.getTariff());
        bill.setToPay(billDTO.getToPay());
        bill.setPaid(billDTO.getPaid());
        bill.setDate(billDTO.getDate());
        bill.setBillStatus(BillStatus.NOT_PAID);
    }

    private static void mapFromBillDTOTOBill(BillDTO billDTO, Bill bill) {
        bill.setTariff(billDTO.getTariff());
        bill.setBillStatus(billDTO.getStatus().equals(BillStatus.PAID.toString()) ?
                BillStatus.PAID :
                BillStatus.NOT_PAID);
        bill.setDate(billDTO.getDate());
        bill.setPaid(billDTO.getPaid());
        bill.setToPay(billDTO.getToPay());
    }
}
