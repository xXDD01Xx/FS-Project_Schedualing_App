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
    private boolean budgetImpacted;
    private String why;
    private boolean approvedStatus;
    private LocalDateTime approveDateTm;

    public ModChangeOrder(int id, String modCoName, int projectId, String type, LocalDate courtDate, boolean scheduleImpacted, boolean budgetImpacted, String why, boolean approvedStatus, LocalDateTime approveDateTm)
    {
        this.id = id;
        this.modCoName = modCoName;
        this.projectId = projectId;
        this.type = type;
        this.courtDate = courtDate;
        this.scheduleImpacted = scheduleImpacted;
        this.budgetImpacted = budgetImpacted;
        this.why = why;
        this.approvedStatus = approvedStatus;
        this.approveDateTm = approveDateTm;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getModCoName()
    {
        return modCoName;
    }

    public void setModCoName(String modCoName)
    {
        this.modCoName = modCoName;
    }

    public int getProjectId()
    {
        return projectId;
    }

    public void setProjectId(int projectId)
    {
        this.projectId = projectId;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public LocalDate getCourtDate()
    {
        return courtDate;
    }

    public void setCourtDate(LocalDate courtDate)
    {
        this.courtDate = courtDate;
    }

    public boolean isScheduleImpacted()
    {
        return scheduleImpacted;
    }

    public void setScheduleImpacted(boolean scheduleImpacted)
    {
        this.scheduleImpacted = scheduleImpacted;
    }

    public boolean isBudgetImpacted()
    {
        return budgetImpacted;
    }

    public void setBudgetImpacted(boolean budgetImpacted)
    {
        this.budgetImpacted = budgetImpacted;
    }

    public String getWhy()
    {
        return why;
    }

    public void setWhy(String why)
    {
        this.why = why;
    }

    public boolean isApprovedStatus()
    {
        return approvedStatus;
    }

    public void setApprovedStatus(boolean approvedStatus)
    {
        this.approvedStatus = approvedStatus;
    }

    public LocalDateTime getApproveDateTm()
    {
        return approveDateTm;
    }

    public void setApproveDateTm(LocalDateTime approveDateTm)
    {
        this.approveDateTm = approveDateTm;
    }
}
