package com.softserve.osbb.controller;

import com.softserve.osbb.dto.BillDTO;
import com.softserve.osbb.dto.SearchDTO;
import com.softserve.osbb.dto.mappers.BillDTOMapper;
import com.softserve.osbb.model.Apartment;
import com.softserve.osbb.model.Bill;
import com.softserve.osbb.model.Provider;
import com.softserve.osbb.model.enums.BillStatus;
import com.softserve.osbb.service.ApartmentService;
import com.softserve.osbb.service.BillService;
import com.softserve.osbb.service.ProviderService;
import com.softserve.osbb.util.BillPageCreator;
import com.softserve.osbb.util.PageCreator;
import com.softserve.osbb.util.PageRequestGenerator;
import com.softserve.osbb.util.resources.BillResourceList;
import com.softserve.osbb.util.resources.EntityResourceList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.softserve.osbb.util.ResourceUtil.toResource;

/**
 * Created by nataliia on 11.07.16.
 */

@RestController
@CrossOrigin
@RequestMapping("/restful/bill")
public class BillController {

    private static Logger logger = LoggerFactory.getLogger(BillController.class);

    @Autowired
    private BillService billService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ProviderService providerService;

    @RequestMapping(value = "/{ids}", method = RequestMethod.GET)
    public List<Bill> findBill(List<Integer> ids) {
        return billService.findAllBillsByIDs(ids);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Bill findOneBill(@PathVariable("id") Integer id) {
        return billService.findOneBillByID(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<PageCreator<Resource<BillDTO>>> findAllBills(
            @RequestParam(value = "status", required = false) String status,
            @RequestBody SearchDTO searchDTO) {
        logger.info(String.format("listing all bills, page number: %d ", searchDTO.getPageNumber()));
        final PageRequest pageRequest = PageRequestGenerator.generatePageRequest(searchDTO.getPageNumber())
                .addRows(searchDTO.getRowNum())
                .addOrderType(searchDTO.getOrderType())
                .addSortedBy(searchDTO.getSortedBy(), "date")
                .toPageRequest();
        Page<Bill> bills = billService.findAllBills(pageRequest);
        if (bills == null || bills.getSize() == 0) {
            logger.error("np bills were found");
            throw new BillNotFoundException();
        }
        PageRequestGenerator.PageSelector pageSelector = PageRequestGenerator
                .generatePageSelectorData(bills);
        EntityResourceList<BillDTO> billResourceList = BillFilter.createFilteredByStatusResourceList(status, bills);
        PageCreator<Resource<BillDTO>> billPageCreator = setUpPageCreator(pageSelector, billResourceList);
        return new ResponseEntity<>(billPageCreator, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}/all", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<PageCreator<Resource<BillDTO>>> listAllBillsByUser(
            @PathVariable("userId") Integer userId,
            @RequestParam(value = "status", required = false) String status,
            @RequestBody SearchDTO searchDTO) {
        logger.info(String.format("listing all bills for user: %d , page number: %d ", userId, searchDTO.getPageNumber()));
        final PageRequest pageRequest = PageRequestGenerator.generatePageRequest(searchDTO.getPageNumber())
                .addRows(searchDTO.getRowNum())
                .addOrderType(searchDTO.getOrderType())
                .addSortedBy(searchDTO.getSortedBy(), "date")
                .toPageRequest();
        Page<Bill> bills = billService.findAllByApartmentOwner(userId, pageRequest);
        if (bills == null || bills.getSize() == 0) {
            logger.error("np bills were found");
            throw new BillNotFoundException();
        }
        PageRequestGenerator.PageSelector pageSelector = PageRequestGenerator
                .generatePageSelectorData(bills);
        EntityResourceList<BillDTO> billResourceList = BillFilter.createFilteredByStatusResourceList(status, bills);
        PageCreator<Resource<BillDTO>> billPageCreator = setUpPageCreator(pageSelector, billResourceList);

        return new ResponseEntity<>(billPageCreator, HttpStatus.OK);
    }


    private static class BillFilter {

        private static final String PAID = "PAID";
        private static final String NOT_PAID = "NOT_PAID";

        public static EntityResourceList<BillDTO> createFilteredByStatusResourceList(String status, Page<Bill> bills) {
            EntityResourceList<BillDTO> billResourceList = new BillResourceList();
            if (status == null) {
                logger.info("default filtering");
                addIfNoStatus(bills, billResourceList);
                return billResourceList;
            }
            switch (status.toUpperCase()) {
                case PAID:
                    addByStatusIfPaid(bills, billResourceList);
                    break;
                case NOT_PAID:
                    addByStatusIfNotPaid(bills, billResourceList);
                    break;
                default:
                    addIfNoStatus(bills, billResourceList);
                    break;

            }
            return billResourceList;
        }

        private static void addIfNoStatus(Page<Bill> bills, EntityResourceList<BillDTO> billResourceList) {
            bills.forEach((bill) -> {
                        BillDTO billDTo = BillDTOMapper.mapEntityToDTO(bill);
                        logger.info("billDto created " + billDTo.toString());
                        billResourceList.add(toResource(billDTo));
                    }
            );
        }

        private static void addByStatusIfNotPaid(Page<Bill> bills, EntityResourceList<BillDTO> billResourceList) {
            logger.info("filtering by: " + BillStatus.NOT_PAID);
            bills.getContent()
                    .stream()
                    .filter((b) -> b.getBillStatus() == BillStatus.NOT_PAID)
                    .forEach((bill) -> {
                        BillDTO billDTo = BillDTOMapper.mapEntityToDTO(bill);
                        logger.info("billDto created " + billDTo.toString());
                        billResourceList.add(toResource(billDTo));
                    });
        }

        private static void addByStatusIfPaid(Page<Bill> bills, EntityResourceList<BillDTO> billResourceList) {
            logger.info("filtering by: " + BillStatus.PAID);
            bills.getContent()
                    .stream()
                    .filter((b) -> b.getBillStatus() == BillStatus.PAID)
                    .forEach((bill) -> {
                        BillDTO billDTo = BillDTOMapper.mapEntityToDTO(bill);
                        logger.info("billDto created " + billDTo.toString());
                        billResourceList.add(toResource(billDTo));
                    });
        }
    }

    private PageCreator<Resource<BillDTO>> setUpPageCreator(PageRequestGenerator.PageSelector pageSelector, EntityResourceList<BillDTO> billResourceList) {
        PageCreator<Resource<BillDTO>> pageCreator = new BillPageCreator();
        pageCreator.setRows(billResourceList);
        pageCreator.setCurrentPage(Integer.valueOf(pageSelector.getCurrentPage()).toString());
        pageCreator.setBeginPage(Integer.valueOf(pageSelector.getBegin()).toString());
        pageCreator.setEndPage(Integer.valueOf(pageSelector.getEnd()).toString());
        pageCreator.setTotalPages(Integer.valueOf(pageSelector.getTotalPages()).toString());
        return pageCreator;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity saveBill(@RequestBody BillDTO saveBillDTO) {
        logger.info("saving bill");
        Bill bill = BillDTOMapper.mapDTOtoEntity(saveBillDTO, billService);
        logger.info("bill" + bill);
        Apartment apartment = apartmentService.findOneApartmentByID(saveBillDTO.getApartmentId());
        Provider provider = providerService.findOneProviderById(saveBillDTO.getProviderId());
        bill.setApartment(apartment);
        bill.setProvider(provider);
        apartmentService.saveApartment(apartment);
        providerService.saveProvider(provider);
        bill = billService.saveBill(bill);
        if (bill == null) {
            logger.warn("bill wasn't saved");
            return new ResponseEntity(HttpStatus.CONTINUE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateBill(@RequestBody BillDTO billDTO) {
        logger.info("updating bill with id" + billDTO.getBillId());
        Bill bill = BillDTOMapper.mapDTOtoEntity(billDTO, billService);
        bill = billService.saveBill(bill);
        if (bill == null) {
            logger.warn("bill wasn't saved as not found");
            throw new BillNotFoundException();
        }
        logger.info("successfully updated bill with id " + bill.getBillId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{billId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("billId") Integer billId) {
        logger.info("deleting bill with id" + billId);
        boolean isDeleted = billService.deleteBillByID(billId);
        if (!isDeleted) {
            logger.warn("bill was not deleted as not found");
            throw new BillNotFoundException();
        }

        return new ResponseEntity(HttpStatus.OK);
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Bills not found")
    private class BillNotFoundException extends RuntimeException {
    }

}
