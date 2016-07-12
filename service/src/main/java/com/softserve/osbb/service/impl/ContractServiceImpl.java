package com.softserve.osbb.service.impl;

import com.softserve.osbb.model.Contract;
import com.softserve.osbb.repository.ContractRepository;
import com.softserve.osbb.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Roma on 12/07/2016.
 */
public class ContractServiceImpl implements ContractService {

    private static final List<Contract> EMPTY_LIST = new CopyOnWriteArrayList<>();
    private static final Contract EMPTY_REPORT = new Contract();

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Contract addContract(Contract contract) throws Exception {
        return contract == null ? null : addContractIfNotExists(contract);
    }
    private Contract addContractIfNotExists(Contract contract) throws Exception {

        boolean isExisted = contractRepository.exists(contract.getContractId());

        if (isExisted) {
            throw new Exception("contract under id: " + contract.getContractId() + " already exists");
        }

        return contractRepository.save(contract);

    }

    @Override
    public Contract updateContract(Integer contractId, Contract contract) throws Exception {
        return contract == null ? null : updateContractIfExists(contractId, contract);
    }

    private Contract updateContractIfExists(Integer contractId, Contract contract) throws Exception {

        boolean isExisted = contractRepository.exists(contractId);

        if (!isExisted) {
            throw new Exception("contract under id: " + contract.getContractId() + " doesn't exist and thus" +
                    " cannot be updated");

        }
        return contractRepository.save(contract);

    }

    @Override
    public Contract getContractById(Integer contractId) throws Exception {
        return contractRepository.findOne(contractId);
    }

    @Override
    public Contract getOneContractBySearchTerm(String searchTerm) throws Exception {
        return (searchTerm != null || !searchTerm.isEmpty()) ?
                contractRepository.getAllContractsBySearchParam(searchTerm)
                        .stream()
                        .findFirst()
                        .get() : EMPTY_REPORT;
    }

    @Override
    public List<Contract> getAllContractsBySearchTerm(String searchTerm) throws Exception {
        return (searchTerm == null || searchTerm.isEmpty()) ? EMPTY_LIST :
                contractRepository.getAllContractsBySearchParam(searchTerm);
    }

    @Override
    public List<Contract> getAllContractsBetweenDates(LocalDateTime from, LocalDateTime to) throws Exception {
        return contractRepository.getAllContractsBetweenDates(from == null ? LocalDateTime.now() : from,
                to == null ? LocalDateTime.now() : to);
    }

    @Override
    public List<Contract> getAllContracts() throws Exception {
        return contractRepository.findAll();
    }

    @Override
    public List<Contract> showLatestContracts() throws Exception {
        return null;
    }

    @Override
    public void deleteAll() throws Exception {
        contractRepository.deleteAll();
    }

    @Override
    public void deleteContractById(Integer contractId) throws Exception {
        contractRepository.delete(contractId);
    }
}
