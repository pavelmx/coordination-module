package com.innowise.coordination.service;

import com.innowise.coordination.entity.Project;
import com.innowise.coordination.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectService extends AbstractService<Project, ProjectRepository> {

    @Autowired
    private ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        super(repository);
    }

    public List<Project> getAllByCustomerId(Long id) {
        return repository.findByCustomerId(id);
    }

    public String setStartProjectById(Long id) {
        Project project = get(id).get();
        LocalDate startDate = LocalDate.now();
        project.setStartDate(startDate);
        repository.save(project);
        String response = "Project '" + project.getName() + "' started at " + startDate;
        return response;
    }

    public String setEndProjectById(Long id) {
        Project project = get(id).get();
        LocalDate endDate = LocalDate.now();
        project.setEndDate(endDate);
        repository.save(project);
        String response = "Project '" + project.getName() + "' finished at " + endDate;
        return response;
    }


    public List<Project> getAllActive() {
        return repository.findByStartDateNotNull();
    }
}
