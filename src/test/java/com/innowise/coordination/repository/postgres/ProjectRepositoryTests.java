package com.innowise.coordination.repository.postgres;

import com.innowise.coordination.entity.Project;
import com.innowise.coordination.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class ProjectRepositoryTests extends AbstractRepositoryTests<Project, ProjectRepository, Long> {

    @Autowired
    private ProjectRepository repository;

    private Project project;

    @Override
    public Project getEntity() {
        project = new Project();
        project = repository.save(project);
        return project;
    }

    @Override
    public Long getEntityId() {
        return project.getId();
    }


}
