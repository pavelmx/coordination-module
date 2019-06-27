package com.innowise.coordination.service;

import com.innowise.coordination.entity.ProjectPositionEmployee;
import com.innowise.coordination.entity.QProjectPositionEmployee;
import com.innowise.coordination.repository.ProjectPositionEmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectPositionEmployeeService extends AbstractService<ProjectPositionEmployee, ProjectPositionEmployeeRepository, QProjectPositionEmployee> {

    public ProjectPositionEmployeeService(ProjectPositionEmployeeRepository repository) {
        super(repository);
    }
}
