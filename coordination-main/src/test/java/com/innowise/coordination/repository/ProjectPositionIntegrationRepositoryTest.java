package com.innowise.coordination.repository;

import com.innowise.coordination.entity.ProjectPosition;
import com.innowise.coordination.entity.QProjectPosition;
import org.springframework.beans.factory.annotation.Autowired;


public class ProjectPositionIntegrationRepositoryTest extends AbstractIntegrationRepositoryTest<ProjectPosition, ProjectPositionRepository, Long, QProjectPosition> {

    @Autowired
    private ProjectPositionRepository repository;

    private ProjectPosition projectPosition;

    @Override
    public ProjectPosition getEntity() {
        projectPosition = new ProjectPosition();
        return projectPosition;
    }



}
