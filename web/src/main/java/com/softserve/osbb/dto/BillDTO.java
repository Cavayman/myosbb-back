package com.softserve.osbb.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.Provider;
import com.softserve.osbb.utils.CustomLocalDateTimeSerializer;

import java.time.LocalDate;

/**
 * Created by nazar.dovhyy on 18.08.2016.
 */
public class BillDTO {

    private Integer billId;
    private LocalDate date;
    private Float tariff;
    private Float toPay;
    private Float paid;
    private String description;
    private Integer apartmentNumber;

    public BillDTO() {

    }

    public BillDTO(BillDTOBuilder billDTOBuilder) {
        this.billId = billDTOBuilder.billId;
        this.date = billDTOBuilder.date;
        this.tariff = billDTOBuilder.tariff;
        this.toPay = billDTOBuilder.toPay;
        this.paid = billDTOBuilder.paid;
        this.description = billDTOBuilder.description;
        this.apartmentNumber = billDTOBuilder.apartmentNumber;
    }

    public Integer getBillId() {
        return billId;
    }

    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    public LocalDate getDate() {
        return date;
    }


    public Float getTariff() {
        return tariff;
    }


    public Float getToPay() {
        return toPay;
    }


    public Float getPaid() {
        return paid;
    }

    public String getDescription() {
        return description;
    }


    public Integer getApartmentNumber() {
        return apartmentNumber;
    }


    public static class BillDTOBuilder extends BillDTO {
        private Integer billId;
        private LocalDate date;
        private Float tariff;
        private Float toPay;
        private Float paid;
        private String description;
        private Integer apartmentNumber;

        public BillDTOBuilder() {

        }

        public BillDTOBuilder setBillId(Integer billId) {
            this.billId = billId;
            return this;
        }

        public BillDTOBuilder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public BillDTOBuilder setTariff(Float tariff) {
            this.tariff = tariff;
            return this;
        }


        public BillDTOBuilder setToPay(Float toPay) {
            this.toPay = toPay;
            return this;
        }


        public BillDTOBuilder setPaid(Float paid) {
            this.paid = paid;
            return this;
        }

        public BillDTOBuilder setDescription(Provider provider) {
            if (provider != null) {
                this.description = provider.getDescription();
            }
            return this;
        }


        public BillDTOBuilder setApartmentNumber(Apartment apartment) {
            if (apartment != null) {
                this.apartmentNumber = apartment.getNumber();
            }
            return this;
        }

        public BillDTO build() {
            return new BillDTO(this);
        }
    }

    @Override
    public String toString() {
        return "BillDTO{" +
                "billId=" + billId +
                ", date=" + date +
                ", tariff=" + tariff +
                ", toPay=" + toPay +
                ", paid=" + paid +
                ", description='" + description + '\'' +
                ", apartmentNumber=" + apartmentNumber +
                '}';
    }
}
