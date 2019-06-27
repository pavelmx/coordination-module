package com.innowise.coordination.repository;

import com.innowise.coordination.entity.Project;
import com.innowise.coordination.entity.QProject;
import org.springframework.beans.factory.annotation.Autowired;


public class ProjectIntegrationRepositoryTest extends AbstractIntegrationRepositoryTest<Project, ProjectRepository, Long, QProject> {

    @Autowired
    private ProjectRepository repository;

    private Project project;

    @Override
    public Project getEntity() {
        project = new Project();
        return project;
    }



}
