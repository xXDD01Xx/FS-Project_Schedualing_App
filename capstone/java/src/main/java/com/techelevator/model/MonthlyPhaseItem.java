package com.techelevator.model;

import java.time.LocalDate;

public class MonthlyPhaseItem {
    private int id;
    private int monthlyScheduleId;
    private int phaseItemId;
    private LocalDate itemDate;
    private int itemTasks;
    private String itemDescription;
    private String phaseDescription;

    public MonthlyPhaseItem() {
    }


    public MonthlyPhaseItem(int id, int monthlyScheduleId, int phaseItemId, LocalDate itemDate, int itemTasks, String itemDescription, String phaseDescription) {
        this.id = id;
        this.monthlyScheduleId = monthlyScheduleId;
        this.phaseItemId = phaseItemId;
        this.itemDate = itemDate;
        this.itemTasks = itemTasks;
        this.itemDescription = itemDescription;
        this.phaseDescription = phaseDescription;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonthlyScheduleId() {
        return monthlyScheduleId;
    }

    public void setMonthlyScheduleId(int monthlyScheduleId) {
        this.monthlyScheduleId = monthlyScheduleId;
    }

    public int getPhaseItemId() {
        return phaseItemId;
    }

    public void setPhaseItemId(int phaseItemId) {
        this.phaseItemId = phaseItemId;
    }

    public LocalDate getItemDate() {
        return itemDate;
    }

    public void setItemDate(LocalDate itemDate) {
        this.itemDate = itemDate;
    }

    public int getItemTasks() {
        return itemTasks;
    }

    public void setItemTasks(int itemTasks) {
        this.itemTasks = itemTasks;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getPhaseDescription() {
        return phaseDescription;
    }

    public void setPhaseDescription(String phaseDescription) {
        this.phaseDescription = phaseDescription;
    }
}

