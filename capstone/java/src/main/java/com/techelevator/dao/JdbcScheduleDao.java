package com.techelevator.dao;

import com.techelevator.model.Project;
import com.techelevator.model.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcScheduleDao implements ScheduleDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduleDao(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public Integer projectIdFromProjectName(String projectName) {
        return null;
    }

    @Override
    public Integer phaseIdFromPhaseDescription(String description) {
        return null;
    }

    @Override
    public List<Schedule> listScheduleItems(int projectId) {
        String sql = "SELECT id, project_id, phase_item, item_date " +
                "FROM project";
        SqlRowSet rs = this.jdbcTemplate.queryForRowSet(sql);
        List<Schedule> schedules = new ArrayList<>();
        while (rs.next()) {
            schedules.add(mapRowToSchedule(rs));
        }
        return schedules;
    }

    @Override
    public void addToSchedule(Project project, Schedule schedule) {
//        String sql = "INSERT INTO "
    }

    @Override
    public void updateScheduleItem(Schedule schedule) {

    }


    @Override
    public void deleteScheduleItem(int id) {
//        String sql = "DELETE FROM "
    }

    private Schedule mapRowToSchedule(SqlRowSet rs) {
        Schedule schedule = new Schedule();
        schedule.setProjectId(rs.getInt("project_id"));
//        schedule.getPhaseItems(rs.getPhaseItems("phase_item"));

        return schedule;
    }

//    need to get project id from project name

//    get phase item id from phase item description

//    add to schedule
//    get list of schedule items for project
//    update schedule item date/tasks
//    delete schedule item
}
