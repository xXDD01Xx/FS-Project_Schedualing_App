package com.techelevator.model;

import java.time.LocalDate;

public class PhaseItem {
    private String phase;
    private String item;
    private LocalDate itemDate;
    private int taskCount;

    public PhaseItem(String phase, String item, LocalDate itemDate, int taskCount) {
        this.phase = phase;
        this.item = item;
        this.itemDate = itemDate;
        this.taskCount = taskCount;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public LocalDate getItemDate() {
        return itemDate;
    }

    public void setItemDate(LocalDate itemDate) {
        this.itemDate = itemDate;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }


}
