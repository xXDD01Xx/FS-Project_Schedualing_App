package com.techelevator.dao;

import com.techelevator.model.MonthlyPhaseItem;

import java.time.LocalDate;
import java.util.List;

public interface MonthlyScheduleDao {

    List<MonthlyPhaseItem> listMonthlyScheduleItems(int monthlyScheduleId);

    void addItemToMonthlySchedule();

    void addNewMonthlySchedule(int projectId, LocalDate monthYear);

    void updateMonthlySchedule();

    void deleteMonthlySchedule();


}
