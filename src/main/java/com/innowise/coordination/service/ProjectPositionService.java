package com.innowise.coordination.service;

import com.innowise.coordination.entity.ProjectPosition;
import com.innowise.coordination.repository.ProjectPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectPositionService extends AbstractService<ProjectPosition, ProjectPositionRepository>{

    @Autowired
    private ProjectPositionRepository repository;

    public ProjectPositionService(ProjectPositionRepository repository) {
        super(repository);
    }

    public List<ProjectPosition> getAllByEmployeeId(Long id) {
        return repository.findByProjectPositionEmployee_EmployeeId(id);
    }
}
