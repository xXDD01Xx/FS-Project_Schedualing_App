package com.techelevator.controller;

import com.techelevator.dao.ScheduleDao;
import com.techelevator.model.BaselinePhaseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ScheduleController {


    @Autowired
    ScheduleDao scheduleDao;

    @RequestMapping(path = "/project/projectname", method = RequestMethod.POST)
    public Integer projectIdFromProjectName(@RequestBody String projectName) {
        return scheduleDao.projectIdFromProjectName(projectName);
    }

    @RequestMapping(path = "/phase/description", method = RequestMethod.POST)
    public Integer phaseIdFromPhaseDescription(@RequestBody String description) {
        return scheduleDao.phaseIdFromPhaseDescription(description);
    }

    @RequestMapping(path = "/schedule/{id}", method = RequestMethod.GET)
    public List<BaselinePhaseItem> listBaselineScheduleItems(@PathVariable int id) {
        return scheduleDao.listBaselineScheduleItems(id);
    }

    @RequestMapping(path = "/schedule/new", method = RequestMethod.POST)
    public void addToBaselineSchedule(@RequestBody BaselinePhaseItem baselinePhaseItem) {
        scheduleDao.addToBaselineSchedule(baselinePhaseItem);
    }

    @RequestMapping(path = "/schedule/update", method = RequestMethod.PUT)
    public void updateScheduleItem(@RequestBody BaselinePhaseItem baselinePhaseItem) {
        scheduleDao.updateBaselineScheduleItem(baselinePhaseItem);
    }

    @RequestMapping(path = "/schedule/{id}/delete", method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable int id) {
        scheduleDao.deleteBaselineScheduleItem(id);
    }

}
