package com.techelevator.dao;

import com.techelevator.model.MonthlyPhaseItem;
import com.techelevator.model.MonthlySchedule;

import java.time.LocalDate;
import java.util.List;

public interface MonthlyScheduleDao {

    List<MonthlySchedule> listAllMonthlySchedule();

    MonthlySchedule listMonthlySchedule(int projectId);

    List<MonthlyPhaseItem> listMonthlyScheduleItems(int monthlyScheduleId);


    Integer addNewMonthlySchedule(int projectId, LocalDate monthYear);

    void addNewMonthlyScheduleItem(MonthlyPhaseItem monthlyPhaseItem);


    void updateMonthlyScheduleItem(MonthlyPhaseItem monthlyPhaseItem);

    void deleteMonthlyScheduleItem(int id);

    void updateMonthlySchedule(MonthlySchedule monthlySchedule);
}
