package com.techelevator.model;

import java.util.Set;

public class Schedule {
    private int projectId;
    private int projectName;
    private Set<PhaseItem> phaseItems;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Set<PhaseItem> getPhaseItems() {
        return phaseItems;
    }

    public void setPhaseItems(Set<PhaseItem> phaseItems) {
        this.phaseItems = phaseItems;
    }

    public int getProjectName() {
        return projectName;
    }

    public void setProjectName(int projectName) {
        this.projectName = projectName;
    }
}
