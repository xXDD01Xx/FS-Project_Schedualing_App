package com.techelevator.dao;

import com.techelevator.model.BaselinePhaseItem;
import com.techelevator.model.Project;
import com.techelevator.model.Schedule;

import java.util.List;

public interface ScheduleDao {
    Integer projectIdFromProjectName(String projectName);

    Integer phaseIdFromPhaseDescription(String description);

    List<BaselinePhaseItem> listBaselineScheduleItems(int projectId);

    void addToBaselineSchedule(BaselinePhaseItem baselinePhaseItem);

    void addNewProjectBaselineItems(int projectId);

    void updateBaselineScheduleItem(BaselinePhaseItem baselinePhaseItem);

    void deleteBaselineScheduleItem(int id);

}
