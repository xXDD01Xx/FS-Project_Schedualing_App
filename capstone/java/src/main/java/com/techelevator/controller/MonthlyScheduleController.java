package com.techelevator.controller;

import com.techelevator.dao.MonthlyScheduleDao;
import com.techelevator.model.MonthlyPhaseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class MonthlyScheduleController {

    @Autowired
    MonthlyScheduleDao monthlyScheduleDao;


    @GetMapping(path = "/monthlyschedule/{id}")
    public List<MonthlyPhaseItem> listMonthlyScheduleItems(@PathVariable int id) {
        return monthlyScheduleDao.listMonthlyScheduleItems(id);
    }


}
