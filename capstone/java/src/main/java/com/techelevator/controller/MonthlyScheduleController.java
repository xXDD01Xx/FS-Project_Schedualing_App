package com.techelevator.controller;

import com.techelevator.dao.MonthlyScheduleDao;
import com.techelevator.model.MonthlyPhaseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(path = "/monthlyschedule/new")
    public void addNewMonthlySchedule(@RequestBody int projectId, LocalDate monthYear) {
        monthlyScheduleDao.addNewMonthlySchedule(projectId, monthYear);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(path = "/monthlyschedule/new/{id}")
    public void addNewMonthlyScheduleItem(@PathVariable int projectId) {
        monthlyScheduleDao.addNewMonthlyScheduleItem(projectId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping(path = "/monthlyschedule/update")
    public void updateMonthlyScheduleItem(@RequestBody MonthlyPhaseItem monthlyPhaseItem) {
        monthlyScheduleDao.updateMonthlyScheduleItem(monthlyPhaseItem);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping(path = "/monthlyschedule/delete")
    public void deleteMonthlyScheduleItem(@PathVariable int id) {
        monthlyScheduleDao.deleteMonthlyScheduleItem(id);
    }


}
