package com.innowise.coordination.repository;

import com.innowise.coordination.entity.ProjectPositionEmployee;
import com.innowise.coordination.entity.QProjectPositionEmployee;
import org.springframework.beans.factory.annotation.Autowired;


public class ProjectPositionEmployeeIntegrationRepositoryTest extends AbstractIntegrationRepositoryTest<ProjectPositionEmployee, ProjectPositionEmployeeRepository, Long, QProjectPositionEmployee> {

    @Autowired
    private ProjectPositionEmployeeRepository repository;

    private ProjectPositionEmployee projectPositionEmployee;

    @Override
    public ProjectPositionEmployee getEntity() {
        projectPositionEmployee = new ProjectPositionEmployee();
        return projectPositionEmployee;
    }



}
