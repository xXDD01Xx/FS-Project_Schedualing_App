package com.techelevator.controller;

import com.techelevator.dao.ReportDao;
import com.techelevator.model.MonthlyPhaseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class ReportController {

    @Autowired
    ReportDao reportDao;


//    @GetMapping(path = "/statusreport/{id}")
//    public List<MonthlyPhaseItem> listMonthlyScheduleItems(@PathVariable int projectId) {
//        return reportDao.listStatusReport(projectId);
//    }
//
//    @GetMapping(path = "/master/")
//    public List<MonthlyPhaseItem> listMonthlyScheduleItems() {
//        return reportDao.listMaster();
//    }


}
