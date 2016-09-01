package com.softserve.osbb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nazar.dovhyy on 01.09.2016.
 */
public class BarChartData  {

    private List<Integer> years;
    private List<InnerBarChart> innerBarChart;

    public List<Integer> getYears() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }

    public List<InnerBarChart> getInnerBarChart() {
        if (innerBarChart == null)
            innerBarChart = new ArrayList<>();
        return innerBarChart;
    }

    public void setInnerBarChart(List<InnerBarChart> innerBarChart) {
        this.innerBarChart = innerBarChart;
    }

    public static class InnerBarChart {
        String month;
        double totalPaid;
        double totalUnPaid;

        public InnerBarChart(String month, double totalPaid, double totalUnPaid) {
            this.month = month;
            this.totalPaid = totalPaid;
            this.totalUnPaid = totalUnPaid;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public double getTotalPaid() {
            return totalPaid;
        }

        public void setTotalPaid(double totalPaid) {
            this.totalPaid = totalPaid;
        }

        public double getTotalUnPaid() {
            return totalUnPaid;
        }

        public void setTotalUnPaid(double totalUnPaid) {
            this.totalUnPaid = totalUnPaid;
        }

        @Override
        public String toString() {
            return "InnerBarChart{" +
                    "month='" + month + '\'' +
                    ", totalPaid=" + totalPaid +
                    ", totalUnPaid=" + totalUnPaid +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BarChartData{" +
                "years=" + years +
                ", innerBarChart=" + innerBarChart +
                '}';
    }
}
