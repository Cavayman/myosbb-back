package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Option;
import com.softserve.osbb.repository.OptionRepository;
import com.softserve.osbb.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roman on 28.07.2016.
 */
@Service
public class OptionServiceImpl implements OptionService{

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public Option addOption(Option option) {
        return optionRepository.saveAndFlush(option);
    }

    @Override
    public Option getOption(Integer id) {
        return optionRepository.findOne(id);
    }

    @Override
    public List<Option> getAllOption() {
        return optionRepository.findAll();
    }

    @Override
    public Option updateOption(Option option) {

        if(optionRepository.exists(option.getOptionId())) {
            return optionRepository.save(option);
        } else {
            throw new IllegalArgumentException("Option with id=" + option.getOptionId()
                    + " doesn't exist. First try to add this option.");
        }
    }

    @Override
    public void deleteOption(Integer id) {
        optionRepository.delete(id);
    }

    @Override
    public void deleteOption(Option option) {
        optionRepository.delete(option);
    }

    @Override
    public void deleteAllOption() {
        optionRepository.deleteAll();
    }
}
