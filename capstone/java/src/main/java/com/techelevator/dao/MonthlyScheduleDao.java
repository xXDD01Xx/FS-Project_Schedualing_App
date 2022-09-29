package com.techelevator.dao;

import com.techelevator.model.MonthlyPhaseItem;

import java.util.List;

public interface MonthlyScheduleDao {

    List<MonthlyPhaseItem> listMonthlyScheduleItems(int monthlyScheduleId);

    void addToMonthlySchedule();

    void updateMonthlySchedule();

    void deleteMonthlySchedule();


}
