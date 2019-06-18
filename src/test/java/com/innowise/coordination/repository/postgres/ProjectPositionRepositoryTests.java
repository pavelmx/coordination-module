package com.innowise.coordination.repository.postgres;

import com.innowise.coordination.entity.ProjectPosition;
import com.innowise.coordination.repository.ProjectPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class ProjectPositionRepositoryTests extends AbstractRepositoryTests<ProjectPosition, ProjectPositionRepository, Long> {

    @Autowired
    private ProjectPositionRepository repository;

    private ProjectPosition projectPosition;

    @Override
    public ProjectPosition getEntity() {
        projectPosition = new ProjectPosition();
        projectPosition = repository.save(projectPosition);
        return projectPosition;
    }

    @Override
    public Long getEntityId() {
        return projectPosition.getId();
    }


}
