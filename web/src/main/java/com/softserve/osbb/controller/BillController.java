package com.softserve.osbb.controller;

import com.softserve.osbb.dto.BillDTO;
import com.softserve.osbb.dto.mappers.BillDTOMapper;
import com.softserve.osbb.model.Bill;
import com.softserve.osbb.service.BillService;
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

@RestController()
@CrossOrigin
@RequestMapping("/restful/bill")
public class BillController {

    private static Logger logger = LoggerFactory.getLogger(BillController.class);

    @Autowired
    private BillService billService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Bill> findAllBills() {
        return billService.findAllBills();
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.GET)
    public List<Bill> findBill(List<Integer> ids) {
        return billService.findAllBillsByIDs(ids);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Bill findOneBill(@PathVariable("id") Integer id) {
        return billService.findOneBillByID(id);
    }


    @RequestMapping(value = "/user/{userId}/all", method = RequestMethod.GET)
    public ResponseEntity<PageCreator<Resource<BillDTO>>> listAllBillsByUser(
            @PathVariable("userId") Integer userId,
            @RequestParam(value = "pageNumber") Integer pageNumber,
            @RequestParam(value = "sortedBy", required = false) String sortedBy,
            @RequestParam(value = "order", required = false) Boolean orderType,
            @RequestParam(value = "rowNum", required = false) Integer rowNum) {
        logger.info(String.format("listing all bills for user: %d , page number: %d ", userId, pageNumber));
        final PageRequest pageRequest = PageRequestGenerator.generatePageRequest(pageNumber)
                .addRows(rowNum)
                .addOrderType(orderType)
                .addSortedBy(sortedBy, "date")
                .toPageRequest();
        Page<Bill> bills = billService.findAllByApartmentOwner(userId, pageRequest);
        if (bills == null) {
            logger.error("np bills were found");
            throw new BillNotFoundException();
        }
        PageRequestGenerator.PageSelector pageSelector = PageRequestGenerator
                .generatePageSelectorData(bills);
        EntityResourceList<BillDTO> billResourceList = new BillResourceList();
        bills.forEach((bill) -> {
                    BillDTO billDTo = BillDTOMapper.mapEntityToDTO(bill);
                    logger.info("billDto created " + billDTo.toString());
                    billResourceList.add(toResource(billDTo));
                }
        );
        PageCreator<Resource<BillDTO>> billPageCreator = setUpPageCreator(pageSelector, billResourceList);

        return new ResponseEntity<>(billPageCreator, HttpStatus.OK);
    }

    private PageCreator<Resource<BillDTO>> setUpPageCreator(PageRequestGenerator.PageSelector pageSelector, EntityResourceList<BillDTO> billResourceList) {
        BillPageCreator pageCreator = new BillPageCreator();
        pageCreator.setRows(billResourceList);
        pageCreator.setCurrentPage(Integer.valueOf(pageSelector.getCurrentPage()).toString());
        pageCreator.setBeginPage(Integer.valueOf(pageSelector.getBegin()).toString());
        pageCreator.setEndPage(Integer.valueOf(pageSelector.getEnd()).toString());
        pageCreator.setTotalPages(Integer.valueOf(pageSelector.getTotalPages()).toString());
        pageCreator.setApartmentId(billResourceList.stream().findFirst().get().getContent().getApartmentNumber());
        return pageCreator;
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Bills not found")
    private class BillNotFoundException extends RuntimeException {
    }

}
