package com.techelevator.controller;

import com.techelevator.model.ContractDTO;
import com.techelevator.model.Project;
import com.techelevator.dao.ProjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ProjectController {


    @Autowired
    ProjectDAO projectDAO;


    @RequestMapping(path = "/projects", method = RequestMethod.GET)
    public List<Project> listAllProjects() {
        return projectDAO.listAllProjects();
    }

    @RequestMapping(path = "/groups/{id}/projects", method = RequestMethod.GET)
    public List<Project> listProjectsByContractId(@PathVariable int contractId) {
        return projectDAO.listProjectsByContractId(contractId);
    }

    @RequestMapping(path = "/project/{id}", method = RequestMethod.GET)
    public Project ListProject(@PathVariable int id) {
        return projectDAO.listProject(id);
    }

    @RequestMapping(path = "/project/new", method = RequestMethod.POST)
    public Project addProject(@RequestBody Project project) {
        return projectDAO.addProject(project);
    }

    @RequestMapping(path = "/project/update", method = RequestMethod.PUT)
    public void updateProject(@RequestBody Project project) {
        projectDAO.updateProject(project);
    }

    @RequestMapping(path = "/project/{id}/delete", method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable int id) {
        projectDAO.deleteProject(id);
    }

    @GetMapping(path = "/contracts")
    public List<ContractDTO> getContractList() {
        return projectDAO.getContractList();
    }
}
