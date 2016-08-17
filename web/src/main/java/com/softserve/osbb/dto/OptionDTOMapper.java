package com.softserve.osbb.dto;

import com.softserve.osbb.model.Option;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman on 14.08.2016.
 */
public class OptionDTOMapper {

    public static OptionDTO mapOptionEntityToDTO(Option option) {
        OptionDTO optionDTO = new OptionDTO();
        if(option != null) {
            optionDTO.setOptionId(option.getOptionId());
            optionDTO.setDescription(option.getDescription());
            optionDTO.setVote(VoteDTOForOptionMapper.mapVoteEntityToDTO(option.getVote()));
            optionDTO.setUsersDTO(UserDTOMapper.mapUserEntityToDTO(option.getUsers()));
        }
        return optionDTO;
    }

    public static List<OptionDTO> mapOptionEntityToDTO(List<Option> optionsList) {
        List<OptionDTO> optionsDTOList = new ArrayList<>();
        if(optionsList != null) {
            for(Option o: optionsList) {
                optionsDTOList.add(mapOptionEntityToDTO(o));
            }
        }
        return optionsDTOList;
    }
}
