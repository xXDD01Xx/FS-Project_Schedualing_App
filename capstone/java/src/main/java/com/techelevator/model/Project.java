package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Project {

    private int id;
    private String projectName;
    private int contractId;
    private LocalDateTime dateReceived;

    private BigDecimal budget;
    private LocalDateTime lastModified;
    private int tasksSubstantial;
    private int taskConstruction;
    private BigDecimal contractProjectId;


    public Project() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public LocalDateTime getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(LocalDateTime dateReceived) {
        this.dateReceived = dateReceived;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }


    public int getTasksSubstantial() {
        return tasksSubstantial;
    }

    public void setTasksSubstantial(int tasksSubstantial) {
        this.tasksSubstantial = tasksSubstantial;
    }

    public int getTaskConstruction() {
        return taskConstruction;
    }

    public void setTaskConstruction(int taskConstruction) {
        this.taskConstruction = taskConstruction;
    }

    public BigDecimal getContractProjectId() {
        return contractProjectId;
    }

    public void setContractProjectId(BigDecimal contractProjectId) {
        this.contractProjectId = contractProjectId;
    }


    public Project(int id, String projectName, int contractId, LocalDateTime dateReceived, BigDecimal budget, LocalDateTime lastModified, int tasksSubstantial, int taskConstruction, BigDecimal contractProjectId) {
        this.id = id;
        this.projectName = projectName;
        this.contractId = contractId;
        this.dateReceived = dateReceived;
        this.budget = budget;
        this.lastModified = lastModified;
        this.tasksSubstantial = tasksSubstantial;
        this.taskConstruction = taskConstruction;
        this.contractProjectId = contractProjectId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", contractId=" + contractId +
                ", dateReceived=" + dateReceived +
                ", budget=" + budget +
                ", lastModified=" + lastModified +
                ", tasksSubstantial=" + tasksSubstantial +
                ", taskConstruction=" + taskConstruction +
                ", contractProjectId=" + contractProjectId +
                '}';
    }
}
