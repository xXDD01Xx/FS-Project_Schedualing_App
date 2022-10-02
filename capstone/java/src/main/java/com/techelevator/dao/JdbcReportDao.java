package com.techelevator.dao;

import com.techelevator.model.MasterSchedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReportDao implements ReportDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcReportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<MasterSchedule> listMaster() {
        List<MasterSchedule> masterSchedules = new ArrayList<>();
        String sql = "SELECT " +
                "contract_name, " +
                "contract_id, " +
                "project_name, " +
                "project_id, " +
                "phase, " +
                "start_dt, " +
                "end_dt, " +
                "duration_days " +
                "FROM schedule_vw;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            masterSchedules.add(mapRowToMasterSchedule(results));
        }
        return masterSchedules;
    }

    private MasterSchedule mapRowToMasterSchedule(SqlRowSet rs) {
        MasterSchedule masterSchedule = new MasterSchedule();

        masterSchedule.setContractName(rs.getString("contract_name"));
        masterSchedule.setContractId(rs.getInt("contract_id"));
        masterSchedule.setProjectName(rs.getString("project_name"));
        masterSchedule.setProjectId(rs.getInt("project_id"));
        masterSchedule.setPhase(rs.getString("phase"));
        if (rs.getDate("start_dt") != null) {
            masterSchedule.setStart(rs.getDate("start_dt").toLocalDate());
        }
        if (rs.getDate("end_dt") != null) {
            masterSchedule.setEnd(rs.getDate("end_dt").toLocalDate());
        }
        masterSchedule.setDurationDays(rs.getInt("duration_days"));

        return masterSchedule;
    }
}
