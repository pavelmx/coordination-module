package com.innowise.coordination.service;

import com.innowise.coordination.entity.ProjectPosition;
import com.innowise.coordination.entity.ProjectPositionEmployee;
import com.innowise.coordination.repository.ProjectPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectPositionService extends AbstractService<ProjectPosition, ProjectPositionRepository>{

    @Autowired
    private ProjectPositionRepository repository;

    @Autowired
    private ProjectPositionEmployeeService projectPositionEmployeeService;

    public ProjectPositionService(ProjectPositionRepository repository) {
        super(repository);
    }

    public List<ProjectPosition> getAllByEmployeeId(Long id) {
        return repository.findByProjectPositionEmployee_EmployeeId(id);
    }

    @Override
    public Optional<ProjectPosition> save(ProjectPosition entity) {
        ProjectPositionEmployee projectPositionEmployee = projectPositionEmployeeService.save(entity.getProjectPositionEmployee()).get();
        entity.setProjectPositionEmployee(projectPositionEmployee);
        return Optional.of(repository.save(entity));
    }

    public String setStartWorkById(Long id) {
        ProjectPosition projectPosition = get(id).get();
        LocalDate startDate = LocalDate.now();
        projectPosition.setStartDate(startDate);
        repository.save(projectPosition);
        String response = "You start work on project '" + projectPosition.getProject().getName() +
                "' at position '" + projectPosition.getProjectPositionEmployee().getPosition() + "'";
        return response;
    }

    public String setEndWorkById(Long id) {
        ProjectPosition projectPosition = get(id).get();
        LocalDate endDate = LocalDate.now();
        projectPosition.setEndDate(endDate);
        repository.save(projectPosition);
        String response = "You finish work on project '" + projectPosition.getProject().getName() +
                "' at position '" + projectPosition.getProjectPositionEmployee().getPosition() + "'";
        return response;
    }
}
