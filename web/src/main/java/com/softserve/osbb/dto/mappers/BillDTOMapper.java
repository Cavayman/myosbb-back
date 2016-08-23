package com.softserve.osbb.dto.mappers;

import com.softserve.osbb.dto.BillDTO;
import com.softserve.osbb.model.Bill;

/**
 * Created by nazar.dovhyy on 18.08.2016.
 */
public class BillDTOMapper {

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
                    .build();
        }
        return billDTO;
    }
}
