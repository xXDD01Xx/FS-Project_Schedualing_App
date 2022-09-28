package com.techelevator.controller;

import com.techelevator.dao.ScheduleDao;
import com.techelevator.model.Project;
import com.techelevator.model.Schedule;
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

    @RequestMapping(path = "/project/{id}/Description", method = RequestMethod.GET)
    public Integer projectIdFromProjectName(@PathVariable String projectName) {
        return scheduleDao.projectIdFromProjectName(projectName);
    }

    @RequestMapping(path = "/phase/{id}/Description", method = RequestMethod.GET)
    public Integer phaseIdFromPhaseDescription(@PathVariable String description) {
        return scheduleDao.phaseIdFromPhaseDescription(description);
    }

    @RequestMapping(path = "/schedule/{id}", method = RequestMethod.GET)
    public List<Schedule> listScheduleItems(@PathVariable int projectId) {
        return scheduleDao.listScheduleItems(projectId);
    }

    @RequestMapping(path = "/schedule/new", method = RequestMethod.POST)
    public void addToSchedule(@RequestBody Project project, Schedule schedule) {
        scheduleDao.addToSchedule(project, schedule);
    }

    @RequestMapping(path = "/schedule/update", method = RequestMethod.PUT)
    public void updateScheduleItem(@RequestBody Schedule schedule) {
        scheduleDao.updateScheduleItem(schedule);
    }

    @RequestMapping(path = "/schedule/{id}/delete", method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable int id) {
        scheduleDao.deleteScheduleItem(id);
    }

}
