package com.innowise.coordination.repository.postgres;

import com.innowise.coordination.entity.ProjectPositionEmployee;
import com.innowise.coordination.repository.ProjectPositionEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class ProjectPositionEmployeeRepositoryTests extends AbstractRepositoryTests<ProjectPositionEmployee, ProjectPositionEmployeeRepository, Long> {

    @Autowired
    private ProjectPositionEmployeeRepository repository;

    private ProjectPositionEmployee projectPositionEmployee;

    @Override
    public ProjectPositionEmployee getEntity() {
        projectPositionEmployee = new ProjectPositionEmployee();
        projectPositionEmployee = repository.save(projectPositionEmployee);
        return projectPositionEmployee;
    }

    @Override
    public Long getEntityId() {
        return projectPositionEmployee.getId();
    }


}
