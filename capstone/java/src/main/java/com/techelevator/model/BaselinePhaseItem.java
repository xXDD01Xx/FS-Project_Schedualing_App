package com.techelevator.model;

import java.time.LocalDate;

public class BaselinePhaseItem {

    private int id;
    private int projectId;
    private int phaseItemId;
    private LocalDate itemDate;
    private int itemTasks;
    private String itemDescription;
    private String phaseDescription;


    public BaselinePhaseItem(int id, int projectId, int phaseItemId, LocalDate itemDate, int itemTasks, String itemDescription, String phaseDescription) {
        this.id = id;
        this.projectId = projectId;
        this.phaseItemId = phaseItemId;
        this.itemDate = itemDate;
        this.itemTasks = itemTasks;
        this.itemDescription = itemDescription;
        this.phaseDescription = phaseDescription;
    }

    public String getPhaseDescription() {
        return phaseDescription;
    }

    public void setPhaseDescription(String phaseDescription) {
        this.phaseDescription = phaseDescription;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public BaselinePhaseItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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
}
