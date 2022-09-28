package com.techelevator.dao;

import com.techelevator.model.Project;
import com.techelevator.model.Schedule;

import java.util.List;

public interface ScheduleDao {
    Integer projectIdFromProjectName(String projectName);
    Integer phaseIdFromPhaseDescription(String description);
    List<Schedule> listScheduleItems(int projectId);
void addToSchedule(Project project, Schedule schedule);
void updateScheduleItem(Schedule schedule);
void deleteScheduleItem(int id);
    
}
