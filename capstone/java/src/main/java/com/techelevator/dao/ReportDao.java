package com.techelevator.dao;

import com.techelevator.model.MasterSchedule;
import com.techelevator.model.StatusReport;

import java.util.List;

public interface ReportDao {

    List<StatusReport> listStatusReport();

    List<MasterSchedule> listMaster();
}
