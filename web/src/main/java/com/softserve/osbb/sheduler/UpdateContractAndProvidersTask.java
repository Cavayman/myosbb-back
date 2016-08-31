package com.softserve.osbb.sheduler;

/**
 * Created by Anastasiia Fedorak on 8/16/16.
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.softserve.osbb.controller.AttachmentController;
import com.softserve.osbb.model.Contract;
import com.softserve.osbb.model.Mail;
import com.softserve.osbb.model.Provider;
import com.softserve.osbb.service.ContractService;
import com.softserve.osbb.service.ProviderService;
import com.softserve.osbb.service.impl.MailSenderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@Component
public class UpdateContractAndProvidersTask {
    private static Logger logger = LoggerFactory.getLogger(AttachmentController.class);
    List<Contract> contracts = new ArrayList<>();
    List<Provider> providers = new ArrayList<>();

    @Autowired
    ProviderService providerService;

    @Autowired
    ContractService contractService;

    @Autowired
    private MailSenderImpl sender;

    @Scheduled(fixedRate = 86400000)
    public void updateContractState(){
        logger.info("Scheduler task: uptd contact state");
        contracts = contractService.findByActiveTrue();
        for (Contract contract : contracts){
            if (contract.getDateFinish().isBefore(LocalDate.now())){
                contract.setActive(false);
                contractService.save(contract);
                logger.info("contract #" +contract.getContractId() + " with " + contract.getProvider().getName() + " is over");
                try {
                    if (contract.getProvider().getEmail() !=null) {
                        sender.send(new Mail(contract.getProvider().getEmail(), "PRIVET", "Contract is over")); //tmp
                        logger.info("successfully send message");
                    }
                } catch (MessagingException e) {
                    logger.error("couldn send message");
                }
            } else {
                logger.info("contract #" +contract.getContractId() + " with " + contract.getProvider().getName() + " is active");
            }
        }
    }

    @Scheduled(fixedRate = 86400000)
    @Transactional
    public void updateProviderState(){
        logger.info("Scheduler task: uptd provider state");
        providers = providerService.findByActiveTrue();
        for (Provider provider : providers){
            Collection<Contract> providerContracts =  provider.getContracts();
            int activeContracts = 0;
            for (Contract contract : providerContracts){
                if (contract.isActive()) activeContracts++;
            }
            if (activeContracts == 0){
                provider.setActive(false);
                providerService.saveProvider(provider);
            }
        }
    }
}