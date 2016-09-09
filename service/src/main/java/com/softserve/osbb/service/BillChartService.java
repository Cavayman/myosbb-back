package com.softserve.osbb.service;

import com.softserve.osbb.model.BarChartData;
import com.softserve.osbb.model.BillChartData;

/**
 * Created by nazar.dovhyy on 28.08.2016.
 */
public interface BillChartService {

    BillChartData getBillChartData();

    BarChartData getBarChartData(Integer year);
}
