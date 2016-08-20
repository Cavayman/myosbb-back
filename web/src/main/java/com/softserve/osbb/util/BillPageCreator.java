package com.softserve.osbb.util;

import com.softserve.osbb.dto.BillDTO;
import org.springframework.hateoas.Resource;

/**
 * Created by nazar.dovhyy on 18.08.2016.
 */
public class BillPageCreator extends PageCreator<Resource<BillDTO>> {

    private Integer apartmentId;

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }
}
