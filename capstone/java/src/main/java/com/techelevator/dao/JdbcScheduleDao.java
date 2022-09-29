package com.techelevator.dao;

import com.techelevator.model.BaselinePhaseItem;
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
        String sql = "SELECT id FROM project WHERE project_name = ?;";
        return jdbcTemplate.queryForObject(sql, Integer.class, projectName);
    }

    @Override
    public Integer phaseIdFromPhaseDescription(String description) {
        String sql = "SELECT id FROM phase_items WHERE item_description = ?;";
        return jdbcTemplate.queryForObject(sql, Integer.class, description);
    }

    @Override
    public List<BaselinePhaseItem> listBaselineScheduleItems(int projectId) {
        String sql = "SELECT id, project_id, phase_item, item_date, item_tasks " +
                "FROM baseline_sched_items;";
        SqlRowSet rs = this.jdbcTemplate.queryForRowSet(sql);
        List<BaselinePhaseItem> baselinePhaseItem = new ArrayList<>();
        while (rs.next()) {
            baselinePhaseItem.add(mapRowToBaselineSchedule(rs));
        }
        return baselinePhaseItem;
    }

    @Override
    public void addToBaselineSchedule(BaselinePhaseItem baselinePhaseItem) {
        String sql = "INSERT INTO baseline_sched_items " +
                "(project_id, phase_item, item_date, item_tasks) " +
                "VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, baselinePhaseItem.getProjectId(), baselinePhaseItem.getPhaseItemId(), baselinePhaseItem.getItemDate(), baselinePhaseItem.getItemTasks());
    }

    @Override
    public void updateBaselineScheduleItem(BaselinePhaseItem baselinePhaseItem) {
        String sql = "UPDATE baseline_sched_items " +
                "SET project_id = ?, phase_item = ?, item_date = ?, item_tasks = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql, baselinePhaseItem.getProjectId(), baselinePhaseItem.getPhaseItemId(), baselinePhaseItem.getItemDate(), baselinePhaseItem.getItemTasks(), baselinePhaseItem.getId());
    }


    @Override
    public void deleteBaselineScheduleItem(int id) {
        String sql = "DELETE FROM baseline_sched_items WHERE id =?;";
        jdbcTemplate.update(sql, id);
    }

    private BaselinePhaseItem mapRowToBaselineSchedule(SqlRowSet rs) {
        BaselinePhaseItem baselinePhaseItem = new BaselinePhaseItem();
        baselinePhaseItem.setProjectId(rs.getInt("project_id"));
        baselinePhaseItem.setPhaseItemId(rs.getInt("phase_item"));
        if (rs.getDate("item_date") != null) {
            baselinePhaseItem.setItemDate(rs.getDate("item_date").toLocalDate());
        }
        baselinePhaseItem.setItemTasks(rs.getInt("item_tasks"));
        return baselinePhaseItem;
    }

    private Project mapRowToProject(SqlRowSet rs) {
        Project project = new Project();
        project.setId(rs.getInt("Id"));
        project.setProjectName(rs.getString("project_name"));
        return project;
    }


//    need to get project id from project name
//    get phase item id from phase item description
//    add to schedule
//    get list of schedule items for project
//    update schedule item date/tasks
//    delete schedule item
}
