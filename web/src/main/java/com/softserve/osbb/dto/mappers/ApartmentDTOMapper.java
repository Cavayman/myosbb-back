package com.softserve.osbb.dto.mappers;

import com.softserve.osbb.dto.ApartmentDTO;
import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.User;

/**
 * Created by Oleg on 20.08.2016.
 */
public class ApartmentDTOMapper {
    public static ApartmentDTO mapApartmentEntityToDTO(Apartment apartment) {
        ApartmentDTO apartmentDTO = new ApartmentDTO();
        if (apartment != null) {
            apartmentDTO.setApartmentId(apartment.getApartmentId());
            apartmentDTO.setNumber(apartment.getNumber());
            apartmentDTO.setSquare(apartment.getSquare());
            if (apartment.getOwner() != null) {
                apartmentDTO.setOwner(apartment.getOwner());

            }
        }
        return apartmentDTO;

    }
}