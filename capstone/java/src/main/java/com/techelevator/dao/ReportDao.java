package com.techelevator.dao;

import com.techelevator.model.MasterSchedule;

import java.util.List;

public interface ReportDao {

//    List<StatusReport> listStatusReport(int projectId);

    List<MasterSchedule> listMaster();
}
