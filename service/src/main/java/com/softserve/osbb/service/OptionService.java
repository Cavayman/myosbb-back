package com.softserve.osbb.service;

import com.softserve.osbb.model.Option;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roman on 28.07.2016.
 */
@Service
public interface OptionService {
    Option addOption(Option option);
    Option getOption(Integer id);
    List<Option> getAllOption();
    Option updateOption(Option option);
    void deleteOption(Integer id);
    void deleteOption(Option option);
    void deleteAllOption();
}
