package com.softserve.osbb.service;

import com.softserve.osbb.model.Contract;


import java.time.LocalDateTime;
import java.util.List;


/**
 * Created by Roma on 12/07/2016.
 */
public interface ContractService {
    Contract addContract (Contract contract) throws Exception;

    Contract updateContract (Integer contractId, Contract contract ) throws Exception;

    Contract getContractById (Integer contractId) throws Exception;

    Contract getOneContractBySearchTerm (String name) throws Exception;

    List<Contract> getAllContractsBySearchTerm(String searchTerm) throws Exception;

    List<Contract> getAllContractsBetweenDates(LocalDateTime from, LocalDateTime to) throws Exception;

    List<Contract> getAllContracts() throws Exception;

    List<Contract> showLatestContracts() throws Exception;

    void deleteAll() throws Exception;

    void deleteContractById(Integer reportId) throws Exception;

}
