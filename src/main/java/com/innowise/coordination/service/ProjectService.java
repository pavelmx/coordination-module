package com.innowise.coordination.service;

import com.innowise.coordination.entity.Project;
import com.innowise.coordination.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
