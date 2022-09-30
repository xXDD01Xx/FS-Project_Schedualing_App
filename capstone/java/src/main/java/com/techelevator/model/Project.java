package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Project {

    private int id;
    private String projectName;
    private int contractId;
    private LocalDate dateReceived;

    private BigDecimal budget;
    private LocalDateTime lastModified;
    private int tasksSubstantial;
    private int tasksConstruction;


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

    public LocalDate getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(LocalDate dateReceived) {
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

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public int getTasksSubstantial() {
        return tasksSubstantial;
    }

    public void setTasksSubstantial(int tasksSubstantial) {
        this.tasksSubstantial = tasksSubstantial;
    }

    public int getTasksConstruction() {
        return tasksConstruction;
    }

    public void setTasksConstruction(int tasksConstruction) {
        this.tasksConstruction = tasksConstruction;
    }


    public Project(int id, String projectName, int contractId, LocalDate dateReceived, BigDecimal budget, LocalDateTime lastModified, int tasksSubstantial, int tasksConstruction) {
        this.id = id;
        this.projectName = projectName;
        this.contractId = contractId;
        this.dateReceived = dateReceived;
        this.budget = budget;
        this.lastModified = lastModified;
        this.tasksSubstantial = tasksSubstantial;
        this.tasksConstruction = tasksConstruction;

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
                ", tasksConstruction=" + tasksConstruction +
                '}';
    }
}
