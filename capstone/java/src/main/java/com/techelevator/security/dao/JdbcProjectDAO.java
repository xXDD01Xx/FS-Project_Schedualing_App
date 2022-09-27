package com.techelevator.security.dao;

import com.techelevator.model.Project;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcProjectDAO implements ProjectDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcProjectDAO(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

//    private Project projectObjectMapper = new Project();

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
}
