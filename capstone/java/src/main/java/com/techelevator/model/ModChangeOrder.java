package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ModChangeOrder
{
    private int id;
    private String modCoName;
    private int projectId;
    private String type;
    private LocalDate courtDate;
    private boolean scheduleImpacted;
    private String whySchedImpact;
    private String howSchedImpact;
    private boolean budgetImpacted;
    private String whyBudgetImpact;
    private String howBudgetImpact;
    private String whySubmit;
    private String whyTwo;
    private String whyThree;
    private String whyFour;
    private String whyFive;
    private boolean approved;
    private LocalDateTime approveDateTm;

 public  ModChangeOrder(){}

    public ModChangeOrder(int id, String modCoName, int projectId, String type, LocalDate courtDate, boolean scheduleImpacted, String whySchedImpact, String howSchedImpact, boolean budgetImpacted, String whyBudgetImpact, String howBudgetImpact, String whySubmit, String whyTwo, String whyThree, String whyFour, String whyFive, boolean approved, LocalDateTime approveDateTm) {
        this.id = id;
        this.modCoName = modCoName;
        this.projectId = projectId;
        this.type = type;
        this.courtDate = courtDate;
        this.scheduleImpacted = scheduleImpacted;
        this.whySchedImpact = whySchedImpact;
        this.howSchedImpact = howSchedImpact;
        this.budgetImpacted = budgetImpacted;
        this.whyBudgetImpact = whyBudgetImpact;
        this.howBudgetImpact = howBudgetImpact;
        this.whySubmit = whySubmit;
        this.whyTwo = whyTwo;
        this.whyThree = whyThree;
        this.whyFour = whyFour;
        this.whyFive = whyFive;
        this.approved = approved;
        this.approveDateTm = approveDateTm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModCoName() {
        return modCoName;
    }

    public void setModCoName(String modCoName) {
        this.modCoName = modCoName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getCourtDate() {
        return courtDate;
    }

    public void setCourtDate(LocalDate courtDate) {
        this.courtDate = courtDate;
    }

    public boolean isScheduleImpacted() {
        return scheduleImpacted;
    }

    public void setScheduleImpacted(boolean scheduleImpacted) {
        this.scheduleImpacted = scheduleImpacted;
    }

    public String getWhySchedImpact() {
        return whySchedImpact;
    }

    public void setWhySchedImpact(String whySchedImpact) {
        this.whySchedImpact = whySchedImpact;
    }

    public String getHowSchedImpact() {
        return howSchedImpact;
    }

    public void setHowSchedImpact(String howSchedImpact) {
        this.howSchedImpact = howSchedImpact;
    }

    public boolean isBudgetImpacted() {
        return budgetImpacted;
    }

    public void setBudgetImpacted(boolean budgetImpacted) {
        this.budgetImpacted = budgetImpacted;
    }

    public String getWhyBudgetImpact() {
        return whyBudgetImpact;
    }

    public void setWhyBudgetImpact(String whyBudgetImpact) {
        this.whyBudgetImpact = whyBudgetImpact;
    }

    public String getHowBudgetImpact() {
        return howBudgetImpact;
    }

    public void setHowBudgetImpact(String howBudgetImpact) {
        this.howBudgetImpact = howBudgetImpact;
    }

    public String getWhySubmit() {
        return whySubmit;
    }

    public void setWhySubmit(String whySubmit) {
        this.whySubmit = whySubmit;
    }

    public String getWhyTwo() {
        return whyTwo;
    }

    public void setWhyTwo(String whyTwo) {
        this.whyTwo = whyTwo;
    }

    public String getWhyThree() {
        return whyThree;
    }

    public void setWhyThree(String whyThree) {
        this.whyThree = whyThree;
    }

    public String getWhyFour() {
        return whyFour;
    }

    public void setWhyFour(String whyFour) {
        this.whyFour = whyFour;
    }

    public String getWhyFive() {
        return whyFive;
    }

    public void setWhyFive(String whyFive) {
        this.whyFive = whyFive;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public LocalDateTime getApproveDateTm() {
        return approveDateTm;
    }

    public void setApproveDateTm(LocalDateTime approveDateTm) {
        this.approveDateTm = approveDateTm;
    }
}
