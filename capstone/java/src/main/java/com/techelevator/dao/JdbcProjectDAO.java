package com.techelevator.dao;

import com.techelevator.model.Project;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcProjectDAO implements ProjectDAO {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProjectDAO(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }


    @Override
    public List<Project> listAllProjects() {
        return null;
    }

    @Override
    public List<Project> listProjectsByContractId(int contractId) {
        return null;
    }

    @Override
    public Project listProject(int id) {
        return null;
    }

    @Override
    public void addProject(Project project) {

    }

    @Override
    public void updateProject(Project project) {

    }

    @Override
    public void deleteProject(int id) {

    }

    private Project mapRowToProject(SqlRowSet rs) {
        Project project = new Project();
        project.setId(rs.getInt("Id"));
        project.setProjectName(rs.getString("project_name"));
        project.setContractId(rs.getInt("contract_id"));
        project.setDateReceived(rs.getTimestamp("date_Received"));
        project.setLastModified(rs.getDate("last_modified"));
        project.setTasksSubstantial(rs.getInt("tasks_substantial"));
        project.setTaskConstruction(rs.getInt("tasks_Construction"));
        return project;
    }
}
