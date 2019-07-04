package com.innowise.coordination.service;

import com.innowise.coordination.entity.Project;
import com.innowise.coordination.entity.QProject;
import com.innowise.coordination.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService extends AbstractService<Project, ProjectRepository, QProject> {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private ProjectPositionService projectPositionService;

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
        return repository.findByStartDateNotNullAndEndDateNull();
    }

    @Override
    public Optional<Project> save(Project entity) {
        if(repository.existsByCode(entity.getCode())){
            throw new EntityNotFoundException("Project with this code already exists");
        }
        return super.save(entity);
    }

    @Override
    public Optional<Project> update(Project entity) {
        if(repository.existsByCode(entity.getCode())){
            throw new EntityNotFoundException("Project with this code already exists");
        }
        if(!repository.existsById(entity.getId())){
            throw new EntityNotFoundException("Project with this id not found");
        }
        return super.update(entity);
    }

    @Override
    public void deleteById(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Project with this id not found");
        }
        super.deleteById(id);
    }
}
