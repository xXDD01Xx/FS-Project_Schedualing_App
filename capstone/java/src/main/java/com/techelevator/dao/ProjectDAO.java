package com.techelevator.dao;

import com.techelevator.model.Project;

import java.util.List;

public interface ProjectDAO {

    List<Project>listAllProjects();
    List<Project> listProjectsByContractId(int contractId);
    Project listProject(int id);
    void addProject(Project project);
    void updateProject(Project project);
    void deleteProject(int id);

}
