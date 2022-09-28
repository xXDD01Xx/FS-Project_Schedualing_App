package com.techelevator.dao;

import com.techelevator.model.Project;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProjectDAO implements ProjectDAO {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProjectDAO(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }


    @Override
    public List<Project> listAllProjects() {
        String sql = "SELECT id,project_name,contract_id,date_received,budget,last_modified,tasks_substantial,tasks_construction " +
                "FROM project";
        SqlRowSet rs = this.jdbcTemplate.queryForRowSet(sql);
        List<Project> projects = new ArrayList<>();
        while (rs.next()) {
            projects.add(mapRowToProject(rs));
        }
        return projects;
    }

    @Override
    public List<Project> listProjectsByContractId(int contractId) {
        String sql = "SELECT * " +
                "FROM project " +
                "WHERE contract_id = ?;";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, contractId);
        List<Project> project = new ArrayList<>();
        while (rs.next()) {
            project.add(mapRowToProject(rs));
        }
        return project;
    }

    @Override
    public Project listProject(int id) {
        String sql = "SELECT * " +
                "FROM project " +
                "WHERE id = ?;";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);
        Project project = new Project();
        if (rs.next()) {
            project = mapRowToProject(rs);
        }
        return project;
    }

    @Override
    public void addProject(Project project) {
        String sql = "INSERT INTO project (id, project_name, contract_id, date_received, budget, last_modified, tasks_substantial, tasks_construction) VALUES (?,?,?,?,?,?,?,?) ";
        jdbcTemplate.update(sql, project.getId(), project.getProjectName(), project.getContractId(), project.getDateReceived(), project.getBudget(), project.getLastModified(), project.getTasksSubstantial(), project.getTasksConstruction());
    }


    @Override
    public void updateProject(Project project) {
        String sql = "UPDATE project SET project_name = ?, contract_id= ?, date_received = ?, budget = ?, last_modified = ?, tasks_substantial = ?, tasks_construction = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql, project.getId(), project.getProjectName(), project.getContractId(), project.getDateReceived(), project.getBudget(), project.getLastModified(), project.getTasksSubstantial(), project.getTasksConstruction());
    }


    @Override
    public void deleteProject(int id) {
        String sql = "DELETE FROM project WHERE id =?";
        jdbcTemplate.update(sql, id);
    }

    private Project mapRowToProject(SqlRowSet rs) {
        Project project = new Project();
        project.setId(rs.getInt("Id"));
        project.setProjectName(rs.getString("project_name"));
        project.setContractId(rs.getInt("contract_id"));
        if (rs.getDate("date_received") != null) {
            project.setDateReceived(rs.getDate("date_received").toLocalDate());
        }
        if (rs.getTimestamp("last_modified") != null) {
            project.setLastModified(rs.getTimestamp("last_modified").toLocalDateTime());
        }
        project.setTasksSubstantial(rs.getInt("tasks_substantial"));
        project.setTasksConstruction(rs.getInt("tasks_Construction"));
        return project;
    }
}


