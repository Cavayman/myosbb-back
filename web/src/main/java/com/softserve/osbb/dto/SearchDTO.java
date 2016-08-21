package com.softserve.osbb.dto;

/**
 * Created by nazar.dovhyy on 21.08.2016.
 */
public class SearchDTO {
    private Integer pageNumber;
    private String sortedBy;
    private Boolean orderType;
    private Integer rowNum;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSortedBy() {
        return sortedBy;
    }

    public void setSortedBy(String sortedBy) {
        this.sortedBy = sortedBy;
    }

    public Boolean getOrderType() {
        return orderType;
    }

    public void setOrderType(Boolean orderType) {
        this.orderType = orderType;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    @Override
    public String toString() {
        return "SearchDTO{" +
                "pageNumber=" + pageNumber +
                ", sortedBy='" + sortedBy + '\'' +
                ", orderType=" + orderType +
                ", rowNum=" + rowNum +
                '}';
    }
}
