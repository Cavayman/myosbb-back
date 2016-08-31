package com.softserve.osbb.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.Provider;
import com.softserve.osbb.model.enums.BillStatus;
import com.softserve.osbb.utils.CustomLocalDateDeserializer;
import com.softserve.osbb.utils.CustomLocalDateSerializer;

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
    private Integer apartmentId;
    private Integer providerId;
    private String status;

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
        this.status = billDTOBuilder.status;

    }

    public Integer getBillId() {
        return billId;
    }

    @JsonSerialize(using = CustomLocalDateSerializer.class)
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

    public String getStatus() {
        return status;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTariff(Float tariff) {
        this.tariff = tariff;
    }

    public void setToPay(Float toPay) {
        this.toPay = toPay;
    }

    public void setPaid(Float paid) {
        this.paid = paid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class BillDTOBuilder {
        private Integer billId;
        private LocalDate date;
        private Float tariff;
        private Float toPay;
        private Float paid;
        private Integer apartmentId = 0;
        private Integer providerId = 0;
        private String description;
        private Integer apartmentNumber;
        private String status;

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

        public BillDTOBuilder setApartmentId(Apartment apartment) {
            if (apartment != null) {
                this.apartmentId = apartment.getApartmentId();
            }
            return this;
        }

        public BillDTOBuilder setProviderId(Provider provider) {
            if (provider != null) {
                this.providerId = provider.getProviderId();
            }
            return this;
        }

        public BillDTOBuilder setDescription(Provider provider) {
            if (provider != null) {
                this.description = provider.getName();
            }
            return this;
        }


        public BillDTOBuilder setApartmentNumber(Apartment apartment) {
            if (apartment != null) {
                this.apartmentNumber = apartment.getNumber();
            }
            return this;
        }

        public BillDTOBuilder setStatus(BillStatus billStatus) {
            if (billStatus != null) {
                this.status = billStatus.toString();
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
