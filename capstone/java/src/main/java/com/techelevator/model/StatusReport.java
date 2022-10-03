package com.techelevator.model;

import java.time.LocalDate;

public class StatusReport {

    private String contractName;
    private int contractId;
    private String projectName;
    private int projectId;
    private int phaseItem;
    private LocalDate itemDate;
    private int itemTasks;
    private LocalDate monthYear;
    private String phase;
    private String itemDescription;

    public StatusReport(){}

    public StatusReport(String contractName, int contractId, String projectName, int projectId, int phaseItem, LocalDate itemDate, int itemTasks, LocalDate monthYear, String phase, String itemDescription) {
        this.contractName = contractName;
        this.contractId = contractId;
        this.projectName = projectName;
        this.projectId = projectId;
        this.phaseItem = phaseItem;
        this.itemDate = itemDate;
        this.itemTasks = itemTasks;
        this.monthYear = monthYear;
        this.phase = phase;
        this.itemDescription = itemDescription;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getPhaseItem() {
        return phaseItem;
    }

    public void setPhaseItem(int phaseItem) {
        this.phaseItem = phaseItem;
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

    public LocalDate getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(LocalDate monthYear) {
        this.monthYear = monthYear;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}
