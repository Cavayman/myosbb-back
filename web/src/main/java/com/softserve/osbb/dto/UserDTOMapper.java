package com.softserve.osbb.dto;

import com.softserve.osbb.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman on 16.08.2016.
 */
public class UserDTOMapper {
    public static UserDTO mapUserEntityToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        if(user != null) {
            userDTO.setUserId(user.getUserId());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setEmail(user.getEmail());
        }
        return userDTO;
    }

    public static List<UserDTO> mapUserEntityToDTO(List<User> usersList) {
        List<UserDTO> usersDTOList = new ArrayList<>();
        if(usersList != null) {
            for(User u: usersList) {
                usersDTOList.add(mapUserEntityToDTO(u));
            }
        }
        return usersDTOList;
    }
}
