package com.softserve.osbb.model.report;

import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.Provider;
import com.softserve.osbb.model.User;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
public class BillReportDTO {

    private Integer billId;
    private String ownerName;
    private Integer apartmentNumber;
    private String providerName;
    private Float amountToPay;
    private Float amountPaid;

    public BillReportDTO() {

    }

    public Integer getBillId() {
        return billId;
    }

    public static BillReportDTO setBillId(Integer billId) {
        BillReportDTO billReportDTO = new BillReportDTO();
        billReportDTO.billId = billId;
        return billReportDTO;
    }

    public BillReportDTO setApartmentNumber(Apartment apartment) {
        if (apartment != null) {
            this.apartmentNumber = apartment.getNumber();
        }
        return this;
    }

    public BillReportDTO setOwnerName(User user) {
        if (user != null) {
            this.ownerName = user.getFirstName() + " " + user.getLastName();
        }
        return this;
    }

    public BillReportDTO setProviderName(Provider provider) {
        if (provider != null) {
            this.providerName = provider.getDescription();
        }
        return this;
    }

    public BillReportDTO setAmountToPay(Float amountToPay) {
        this.amountToPay = (amountToPay != null) ?
                amountToPay : 0.0f;
        return this;
    }

    public BillReportDTO setAmountPaid(Float amountPaid) {
        this.amountPaid = (amountPaid != null) ?
                amountPaid : 0.0f;
        return this;
    }

    public String getOwnerName() {
        return ownerName;
    }


    public Integer getApartmentNumber() {
        return apartmentNumber;
    }


    public String getProviderName() {
        return providerName;
    }


    public Float getAmountToPay() {
        return amountToPay;
    }


    public Float getAmountPaid() {
        return amountPaid;
    }

}
