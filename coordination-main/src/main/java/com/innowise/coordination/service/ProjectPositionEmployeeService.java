package com.innowise.coordination.service;

import com.innowise.coordination.entity.ProjectPosition;
import com.innowise.coordination.entity.ProjectPositionEmployee;
import com.innowise.coordination.entity.QProjectPositionEmployee;
import com.innowise.coordination.repository.ProjectPositionEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectPositionEmployeeService extends AbstractService<ProjectPositionEmployee, ProjectPositionEmployeeRepository, QProjectPositionEmployee> {

    @Autowired
    private ProjectPositionService projectPositionService;

    @Autowired
    private ProjectPositionEmployeeRepository repository;

    public ProjectPositionEmployeeService(ProjectPositionEmployeeRepository repository) {
        super(repository);
    }

    public List<ProjectPositionEmployee> getEmployeesByProject(Long id_project) {
        List<ProjectPosition> p = projectPositionService.getAllByProjectId(id_project);
        List<ProjectPositionEmployee> pp = new ArrayList<>();
        p.forEach(projectPosition -> pp.add(projectPosition.getProjectPositionEmployee()));
        return pp;
    }

}
