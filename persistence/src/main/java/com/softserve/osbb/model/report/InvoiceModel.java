package com.softserve.osbb.model.report;

import com.softserve.osbb.model.Provider;
import com.softserve.osbb.model.User;

/**
 * Created by nazar.dovhyy on 29.07.2016.
 */
public class InvoiceModel {

    private Integer billId;
    private String customerName;
    private String customerEmail;
    private String providerDescription;
    private Float amountToPay;
    private Float amountPaid;


    public InvoiceModel() {

    }

    public Integer getBillId() {
        return billId;
    }

    public static InvoiceModel setBillId(Integer billId) {
        InvoiceModel invoiceModel = new InvoiceModel();
        invoiceModel.billId = billId;
        return invoiceModel;
    }


    public InvoiceModel setCustomerName(User user) {
        if (user != null) {
            this.customerName = user.getFirstName() + " " + user.getLastName();
        }
        return this;
    }

    public InvoiceModel setCustomerEmail(User user) {
        if (user != null) {
            this.customerEmail = user.getEmail();
        }
        return this;
    }

    public InvoiceModel setProviderDescription(Provider provider) {
        if (provider != null) {
            this.providerDescription = provider.getDescription();
        }
        return this;
    }

    public InvoiceModel setAmountToPay(Float amountToPay) {
        this.amountToPay = (amountToPay != null) ?
                amountToPay : 0.0f;
        return this;
    }

    public InvoiceModel setAmountPaid(Float amountPaid) {
        this.amountPaid = (amountPaid != null) ?
                amountPaid : 0.0f;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getProviderDescription() {
        return providerDescription;
    }


    public Float getAmountToPay() {
        return amountToPay;
    }


    public Float getAmountPaid() {
        return amountPaid;
    }

}
