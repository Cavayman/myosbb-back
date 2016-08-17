package com.softserve.osbb.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Created by nazar.dovhyy on 17.08.2016.
 */
public class PageRequestGenerator {

    private static final int DEF_ROWS = 10;
    private PageRequestHolder pageRequestHolder;
    private static String defaultSortingFiled = "";

    private Integer currentPage;


    public static class PageRequestHolder {
        private Integer pageNumber;
        private Integer rowNum;
        private String sortedBy;
        private Boolean order;

        public PageRequestHolder(Integer pageNumber) {
            this.pageNumber = pageNumber;
        }

        public PageRequestHolder(Integer pageNumber, Integer rowNum) {
            this(pageNumber);
            this.rowNum = rowNum;
        }
    }

    public PageRequestGenerator(PageRequestHolder pageRequestHolder) {
        this.pageRequestHolder = pageRequestHolder;
    }

    public static PageRequestGenerator generatePageRequest(Integer pageNumber) {
        PageRequestGenerator pageRequestGen = new PageRequestGenerator(new PageRequestHolder(pageNumber));
        return pageRequestGen;
    }

    public PageRequestGenerator addRows(Integer rowNum) {
        this.pageRequestHolder.rowNum = rowNum;
        return this;
    }

    public PageRequestGenerator addSortedBy(String sortedBy, String defaultSorting) {
        this.pageRequestHolder.sortedBy = sortedBy;
        defaultSortingFiled = defaultSorting;
        return this;
    }

    public PageRequestGenerator addOrderType(Boolean order) {
        this.pageRequestHolder.order = order;
        return this;
    }

    public PageRequest gen() {
        PageRequest pageRequest = new PageRequest(
                addPageNumber(),
                addRowNum(),
                addSortingOrderType(),
                addSortedByField());
        return pageRequest;
    }

    private String addSortedByField() {
        return this.pageRequestHolder.sortedBy == null ?
                this.defaultSortingFiled :
                this.pageRequestHolder.sortedBy;
    }

    private Sort.Direction addSortingOrderType() {
        return getSortingOrder(this.pageRequestHolder.order);
    }

    private int addPageNumber() {
        return this.pageRequestHolder.pageNumber - 1;
    }

    private int addRowNum() {
        return this.pageRequestHolder.rowNum == null ? DEF_ROWS : this.pageRequestHolder.rowNum;
    }

    private Sort.Direction getSortingOrder(Boolean order) {
        if (order == null) {
            return Sort.Direction.DESC;
        }
        return order == true ? Sort.Direction.ASC : Sort.Direction.DESC;
    }
}
